package com.example.googlekeeponsterioids.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlekeeponsterioids.domain.model.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NotesViewModel : ViewModel() {
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes: StateFlow<List<Note>> = _notes.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        // TODO: Implement search functionality
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            // TODO: Implement note addition
            val currentNotes = _notes.value.toMutableList()
            currentNotes.add(note)
            _notes.value = currentNotes
        }
    }

    fun togglePinNote(noteId: Int) {
        viewModelScope.launch {
            // TODO: Implement pin/unpin functionality
        }
    }
}
