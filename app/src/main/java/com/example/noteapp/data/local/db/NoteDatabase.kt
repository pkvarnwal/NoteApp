package com.example.noteapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.noteapp.data.local.entity.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(TagConverter::class)
abstract class NoteDatabase: RoomDatabase() {
    abstract fun notesDao(): NoteDao
}