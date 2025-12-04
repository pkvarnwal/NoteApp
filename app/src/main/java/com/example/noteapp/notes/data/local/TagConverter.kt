package com.example.noteapp.notes.data.local

import androidx.room.TypeConverter

object TagConverter {

    private const val COMMA = ","

    @TypeConverter
    @JvmStatic
    fun fromTagList(tags: List<String>?): String {
        if (tags.isNullOrEmpty()) return ""
        return tags.map { it.trim() }.filter { it.isNotEmpty() }.joinToString(COMMA)
    }

    @TypeConverter
    @JvmStatic
    fun toTagList(serialized: String?): List<String> {
        if(serialized.isNullOrBlank()) return emptyList()

        return serialized.split(COMMA).map { it.trim() }.filter { it.isNotEmpty() }
    }
}