package com.example.googlekeeponsterioids

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
    HomeScreen(
        onCreateNote = { /* TODO: Implement note creation */ },
        modifier = modifier
    )
}