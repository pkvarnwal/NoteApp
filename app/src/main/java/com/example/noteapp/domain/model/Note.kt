package com.example.noteapp.domain.model

data class Note(
    val id: Long,
    val title: String,
    val body: String,
    val tags: List<String>,
    val date: String
)