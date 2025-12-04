package com.example.noteapp.notes.data.mapper

import com.example.noteapp.notes.data.Note
import com.example.noteapp.notes.data.local.NoteEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val displayFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

fun NoteEntity.toNote() : Note {
    return Note(
        id = id.toString(),
        title = title,
        body = body,
        tags = tags,
        date = displayFormatter.format(Date(  createdAt))
    )
}