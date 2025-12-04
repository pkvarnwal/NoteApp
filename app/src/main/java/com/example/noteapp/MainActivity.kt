package com.example.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.noteapp.presentation.notes_list.NotesScreen
import com.example.noteapp.presentation.notes_list.NotesViewModel
import com.example.noteapp.ui.theme.NoteAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                val viewModel = hiltViewModel<NotesViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                NotesScreen(
                    viewModel = viewModel,
                    state = state
                )
            }
        }
    }
}