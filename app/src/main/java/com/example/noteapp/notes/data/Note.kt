package com.example.noteapp.notes.data

data class Note(
    val id: String,
    val title: String,
    val body: String,
    val tags: List<String>,
    val date: String
)