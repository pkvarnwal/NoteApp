package com.example.noteapp.notes.data

import androidx.room.Query
import androidx.room.RoomDatabase
import com.example.noteapp.notes.data.local.NoteDatabase
import com.example.noteapp.notes.data.local.NoteEntity
import com.example.noteapp.notes.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val db: NoteDatabase): NoteRepository {

    override fun getNotes(): Flow<List<NoteEntity>> {
       return db.notesDao().getNotes()
    }

    override suspend fun saveNote(note: NoteEntity) {
        db.notesDao().insertNote(note)
    }

    override suspend fun deleteNote(id: Long) {
        db.notesDao().deleteNote(id)
    }

    override fun searchNotes(query: String): Flow<List<NoteEntity>> {
        return db.notesDao().searchNotes(query)
    }

}