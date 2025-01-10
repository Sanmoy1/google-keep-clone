package com.example.googlekeeponsterioids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.googlekeeponsterioids.navigation.Screen
import com.example.googlekeeponsterioids.ui.screens.CreateNoteScreen
import com.example.googlekeeponsterioids.ui.screens.HomeScreen
import com.example.googlekeeponsterioids.ui.theme.GoogleKeepOnSterioidsTheme
import com.example.googlekeeponsterioids.viewmodel.NotesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GoogleKeepOnSterioidsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesApp()
                }
            }
        }
    }
}

@Composable
fun NotesApp(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = viewModel()
) {
    val navController = rememberNavController()
    val notes by viewModel.notes.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                notes = notes,
                onCreateNote = { navController.navigate(Screen.CreateNote.route) },
                onSearchQueryChange = viewModel::updateSearchQuery,
                onNoteClick = { /* TODO: Implement note editing */ },
                onNotePinClick = { note -> 
                    viewModel.toggleNotePinStatus(note.id, !note.isPinned)
                }
            )
        }
        composable(Screen.CreateNote.route) {
            CreateNoteScreen(
                onNavigateBack = { navController.popBackStack() },
                onSaveNote = { note ->
                    viewModel.addNote(note)
                }
            )
        }
    }
}