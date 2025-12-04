package com.example.noteapp.presentation.notes_list

import com.example.noteapp.domain.model.Note

data class NotesState(
    val id : Long = 0,
    val notes: List<Note> = emptyList(),
    val query: String = "",
    val tags: List<String> = emptyList(),
    val selectedTags: List<String> = emptyList(),
    val showAddNoteSheet: Boolean = false
)


