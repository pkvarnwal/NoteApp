package com.example.noteapp.notes.presentation.notes_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.room.util.splitToIntList
import com.example.noteapp.notes.data.Note
import com.example.noteapp.notes.presentation.component.AddNoteSheet
import com.example.noteapp.notes.presentation.component.NoteCard
import com.example.noteapp.notes.presentation.component.SearchBar
import com.example.noteapp.notes.presentation.component.Tags

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel = hiltViewModel<NotesViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    if (state.showAddNoteSheet) {
        AddNoteSheet(
            onDismiss = {
                viewModel.onDismissAddNoteSheet()
            },
            onSave =  { title, body, tags ->
                viewModel.onSaveNote(
                    title = title,
                    body   = body,
                    tags = tags
                )
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.showAddNoteSheet()
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
                ) {
                    Text(
                        text = "Notes Manager",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Text(
                        text = "Organise your notes with tags and searches"
                    )
                }
            }

            stickyHeader {
                SearchBar(
                    query = state.query,
                    onQueryChange = {
                        viewModel.updateSearchQuery(it)
                      // viewModel.searchNotes(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = "Filter by tags:",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Tags(
                        tags = state.tags,
                        selected = state.selectedTags,
                        onSelect = {
                            viewModel.updateSelectedTags(it)
                        }
                    )
                }
            }

            items(state.notes) { note ->
                NoteCard(
                    note = note,
                    onDelete = {viewModel.deleteNote(id = note.id.toLong())},
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

val note = Note(
    id = "1",
    title = "Sample Note 1",
    body = "Sample Body 1",
    tags = listOf("Shopping", "Home"),
    date = "29 Jun, 2025"
)

@Preview
@Composable
fun PreviewNoteScreen() {
    NotesScreen()
}