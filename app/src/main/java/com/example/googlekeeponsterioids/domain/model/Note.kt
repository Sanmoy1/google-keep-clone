package com.example.googlekeeponsterioids.domain.model

data class Note(
    val id: Int = 0,
    val title: String,
    val content: String,
    val color: Int,
    val timestamp: Long,
    val isPinned: Boolean = false
)
