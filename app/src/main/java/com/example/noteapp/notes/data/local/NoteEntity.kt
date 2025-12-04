package com.example.noteapp.notes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant


@Entity(tableName = "notes")
data class NoteEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val title: String,
    val body: String,
    val tags: List<String>,
    val createdAt: Long = System.currentTimeMillis()
)