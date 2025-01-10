package com.example.googlekeeponsterioids.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import com.example.googlekeeponsterioids.domain.model.Note
import com.example.googlekeeponsterioids.ui.theme.noteColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNoteScreen(
    onNavigateBack: () -> Unit,
    onSaveNote: (Note) -> Unit,
    modifier: Modifier = Modifier
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(noteColors.first()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Note") },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            if (title.isNotBlank() || content.isNotBlank()) {
                                onSaveNote(
                                    Note(
                                        title = title,
                                        content = content,
                                        color = selectedColor.toArgb(),
                                        timestamp = System.currentTimeMillis()
                                    )
                                )
                            }
                            onNavigateBack()
                        }
                    ) {
                        Icon(Icons.Default.Check, contentDescription = "Save Note")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(selectedColor)
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = { Text("Title") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = content,
                onValueChange = { content = it },
                placeholder = { Text("Note") },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            ColorSelector(
                colors = noteColors,
                selectedColor = selectedColor,
                onColorSelected = { selectedColor = it }
            )
        }
    }
}

@Composable
fun ColorSelector(
    colors: List<Color>,
    selectedColor: Color,
    onColorSelected: (Color) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(colors) { color ->
            ColorItem(
                color = color,
                isSelected = color == selectedColor,
                onClick = { onColorSelected(color) }
            )
        }
    }
}

@Composable
fun ColorItem(
    color: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        border = if (isSelected) {
            ButtonDefaults.outlinedButtonBorder
        } else null,
        modifier = modifier.size(48.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        )
    }
}
