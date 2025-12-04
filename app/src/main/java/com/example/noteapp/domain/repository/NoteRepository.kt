package com.example.noteapp.domain.repository

import com.example.noteapp.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    fun getNotes(): Flow<List<NoteEntity>>
    suspend fun saveNote(note: NoteEntity)
    suspend fun deleteNote(id: Long)

    fun searchNotes(query: String) : Flow<List<NoteEntity>>
}