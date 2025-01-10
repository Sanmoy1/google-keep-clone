package com.example.googlekeeponsterioids.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.googlekeeponsterioids.data.local.NotesDatabase
import com.example.googlekeeponsterioids.data.repository.NotesRepository
import com.example.googlekeeponsterioids.domain.model.Note
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class NotesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NotesRepository = NotesRepository(
        NotesDatabase.getInstance(application).noteDao
    )
    
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()
    
    val notes: StateFlow<List<Note>> = searchQuery
        .debounce(300L)
        .flatMapLatest { query ->
            if (query.isBlank()) {
                repository.getAllNotes()
            } else {
                repository.searchNotes(query)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            repository.deleteNote(note)
        }
    }

    fun toggleNotePinStatus(noteId: Int, isPinned: Boolean) {
        viewModelScope.launch {
            repository.toggleNotePinStatus(noteId, isPinned)
        }
    }
}
