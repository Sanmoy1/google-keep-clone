package com.example.googlekeeponsterioids.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.googlekeeponsterioids.domain.model.Note

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val content: String,
    val color: Int,
    val timestamp: Long,
    val isPinned: Boolean = false
) {
    fun toNote(): Note {
        return Note(
            id = id,
            title = title,
            content = content,
            color = color,
            timestamp = timestamp,
            isPinned = isPinned
        )
    }
}

fun Note.toNoteEntity(): NoteEntity {
    return NoteEntity(
        id = id,
        title = title,
        content = content,
        color = color,
        timestamp = timestamp,
        isPinned = isPinned
    )
}
