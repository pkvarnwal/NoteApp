package com.example.noteapp.presentation.notes_list

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.noteapp.R
import com.example.noteapp.domain.model.Note
import com.example.noteapp.presentation.component.AddNoteSheet
import com.example.noteapp.presentation.component.NoteCard
import com.example.noteapp.presentation.component.NoteListHeader
import com.example.noteapp.presentation.component.SearchBar
import com.example.noteapp.presentation.component.Tags
import com.example.noteapp.presentation.component.TagsWithTitle

@Composable
fun NotesScreen(
    viewModel: NotesViewModel,
    state: NotesState,
    modifier: Modifier = Modifier
) {
    if (state.showAddNoteSheet) {
        AddNoteSheet(
            onDismiss = {
                viewModel.onDismissAddNoteSheet()
            },
            onSave = { title, body, tags ->
                viewModel.onSaveNote(
                    title = title,
                    body = body,
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
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {

            item {
                NoteListHeader(
                    modifier = Modifier
                        .padding(
                            horizontal = 16.dp,
                            vertical = 16.dp
                        ),
                    title = stringResource(id = R.string.title),
                    subtitle = stringResource(id = R.string.subtitle)
                )
            }

            stickyHeader {
                SearchBar(
                    query = state.query,
                    onQueryChange = {
                        viewModel.updateSearchQuery(it)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                )
            }

            item {
                TagsWithTitle(
                    title = stringResource(R.string.filter_by_tags),
                    tags = state.tags,
                    selectedTags = state.selectedTags,
                    modifier =  Modifier.padding(horizontal = 16.dp),
                    onSelect = {
                        viewModel.updateSelectedTags(it)
                    }
                )
            }

            items(state.notes) { note ->
                NoteCard(
                    note = note,
                    onDelete = { viewModel.deleteNote(id = note.id) },
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}