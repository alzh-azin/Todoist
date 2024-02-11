package com.example.todoist.presentation

sealed interface LoginState {

    data object Loading : LoginState

    data object Success : LoginState

    data class Error(val message: String) : LoginState
}