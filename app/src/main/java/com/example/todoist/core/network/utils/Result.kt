package com.example.todoist.core.network.utils

sealed class Result<out T> {
    class Loading<out T>(isLoading: Boolean) : Result<T>()
    class Success<out T>(val data: T?) : Result<T>()
    class Error<out T>(val code: String? = null, val errorMessage: String?) : Result<T>()
    class Exception<out T>(val exceptionMessage: String?) : Result<T>()
}