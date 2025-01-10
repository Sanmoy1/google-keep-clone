package com.example.googlekeeponsterioids.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object CreateNote : Screen("create_note")
}
