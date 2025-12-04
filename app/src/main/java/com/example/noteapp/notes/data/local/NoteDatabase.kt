package com.example.noteapp.notes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TagConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun notesDao(): NoteDao
}