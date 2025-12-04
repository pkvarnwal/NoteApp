package com.example.noteapp.notes.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes")
    fun getNotes(): Flow<List<NoteEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Query("DELETE FROM notes WHERE id = :id")
    suspend fun deleteNote(id: Long)

    @Query(
        """SELECT * FROM notes WHERE title LIKE '%' || :query || '%'
        OR body LIKE '%' || :query || '%'
        OR CAST(tags AS TEXT) LIKE '%' || :query || '%'
    """)
    fun searchNotes(query: String) : Flow<List<NoteEntity>>
}
