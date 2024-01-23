package com.example.todoist

sealed class Routes(val route: String) {
    object Home : Routes("home")
    object Login : Routes("login")
}