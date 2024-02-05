package com.example.todoist

sealed class Routes(val route: String) {
    object Landing : Routes("landing")
    object Home : Routes("home")
    object Login : Routes("login")
}