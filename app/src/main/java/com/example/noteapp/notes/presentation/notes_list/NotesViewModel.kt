package com.example.noteapp.notes.presentation.notes_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.noteapp.notes.data.local.NoteEntity
import com.example.noteapp.notes.data.mapper.toNote
import com.example.noteapp.notes.domain.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private val _state = MutableStateFlow(NotesState())
    val state = _state.asStateFlow()


    init {
        //searchNotes(query = _state.value.query)
        // observeSearchQuery()
        getAllNotes()
        observeNotes()
    }

    fun onDismissAddNoteSheet() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showAddNoteSheet = false
                )
            }
        }
    }

    fun showAddNoteSheet() {
        viewModelScope.launch {
            _state.update {
                it.copy(
                    showAddNoteSheet = true
                )
            }
        }
    }

    fun onSaveNote(title: String, body: String, tags: String) {
        viewModelScope.launch {
            val note = NoteEntity(
                title = title,
                body = body,
                tags = if (tags.isBlank()) emptyList()
                else tags.split(",").map { it.trim() },
            )
            noteRepository.saveNote(note)
        }
    }

    fun deleteNote(id: Long) {
        viewModelScope.launch {
            noteRepository.deleteNote(id)
        }
    }

//    fun updateSearchQuery(query: String) {
//        viewModelScope.launch {
//            _state.update {
//                it.copy(
//                    query = query
//                )
//            }
//        }
//        searchNotes(_state.value.query)
//    }

    fun searchNotes(query: String) {
        viewModelScope.launch {
            noteRepository.searchNotes(_state.value.query).collect { notes ->
                _state.value = state.value.copy(
                    notes = notes.map { it.toNote() },
                    showAddNoteSheet = false
                )
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _state.update { it.copy(query = query) }
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun observeSearchQuery() {
        viewModelScope.launch {
            _state
                .map { it.query }
                .debounce(300)
                .flatMapLatest { noteRepository.searchNotes(it) }
                .collect { notes ->
                    _state.update {
                        it.copy(
                            notes = notes.map { it.toNote() },
                            showAddNoteSheet = false,
                            tags = notes.flatMap { it.tags }.distinct()
                        )
                    }
                }
        }
    }

    fun updateSelectedTags(tag: String) {
        val selectedTags = _state.value.selectedTags.toMutableList()
        if (selectedTags.contains(tag)) {
            selectedTags.remove(tag)
        } else selectedTags.add(tag)

        viewModelScope.launch {
            _state.update {
                it.copy(
                    selectedTags = selectedTags
                )
            }
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    fun observeNotes() {
        viewModelScope.launch {
            combine(
                _state.map { it.query }.debounce(300),
                _state.map { it.selectedTags }
            ) { query, selectedTags ->
                query to selectedTags
            }
                .flatMapLatest { (query, selectedTags) ->
                    noteRepository.searchNotes(query)
                        .map { notes ->
                            if (selectedTags.isEmpty()) notes
                            else notes.filter { note ->
                                selectedTags.all { tag -> note.tags.contains(tag) }
                            }
                        }
                }
                .collect { filteredNotes ->
                    _state.update {
                        it.copy(
                            notes = filteredNotes.map { it.toNote() },
                            showAddNoteSheet = false,
                            // build the dynamic tag list from ALL notes (not filtered)
                           // tags = filteredNotes.flatMap { note -> note.tags }.distinct()
                        )
                    }
                }
        }
    }


    fun getAllNotes() {
        viewModelScope.launch {
            noteRepository.getNotes().collect { noteEntities ->
                _state.update {
                    it.copy(
                        tags = noteEntities.flatMap { note -> note.tags }.distinct()
                    )
                }
            }
        }
    }
}