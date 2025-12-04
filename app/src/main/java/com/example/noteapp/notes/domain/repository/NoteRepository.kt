package com.example.noteapp.notes.domain.repository

import com.example.noteapp.notes.data.Note
import com.example.noteapp.notes.data.local.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<NoteEntity>>
    suspend fun saveNote(note: NoteEntity)
    suspend fun deleteNote(id: Long)

    fun searchNotes(query: String) : Flow<List<NoteEntity>>
}