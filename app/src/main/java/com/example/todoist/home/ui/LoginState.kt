package com.example.todoist.home.ui

sealed interface LoginState {

    data object Loading : LoginState

    data object Success : LoginState

    data class Error(val message: String) : LoginState
}