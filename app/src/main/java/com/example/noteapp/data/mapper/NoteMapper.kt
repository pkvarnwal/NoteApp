package com.example.noteapp.data.mapper

import com.example.noteapp.domain.model.Note
import com.example.noteapp.data.local.entity.NoteEntity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val displayFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

fun NoteEntity.toNote() : Note {
    return Note(
        id = id,
        title = title,
        body = body,
        tags = tags,
        date = displayFormatter.format(Date(  createdAt))
    )
}