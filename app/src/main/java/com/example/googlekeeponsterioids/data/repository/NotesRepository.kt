package com.example.googlekeeponsterioids.data.repository

import com.example.googlekeeponsterioids.data.local.dao.NoteDao
import com.example.googlekeeponsterioids.data.local.entity.toNoteEntity
import com.example.googlekeeponsterioids.domain.model.Note
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepository(
    private val noteDao: NoteDao
) {
    fun getAllNotes(): Flow<List<Note>> {
        return noteDao.getAllNotes().map { entities ->
            entities.map { it.toNote() }
        }
    }

    fun searchNotes(query: String): Flow<List<Note>> {
        return noteDao.searchNotes(query).map { entities ->
            entities.map { it.toNote() }
        }
    }

    suspend fun getNoteById(id: Int): Note? {
        return noteDao.getNoteById(id)?.toNote()
    }

    suspend fun insertNote(note: Note) {
        noteDao.insertNote(note.toNoteEntity())
    }

    suspend fun deleteNote(note: Note) {
        noteDao.deleteNote(note.toNoteEntity())
    }

    suspend fun toggleNotePinStatus(noteId: Int, isPinned: Boolean) {
        noteDao.updateNotePinStatus(noteId, isPinned)
    }
}
