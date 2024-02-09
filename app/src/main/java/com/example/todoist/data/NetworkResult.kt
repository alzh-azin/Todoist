package com.example.todoist.data

sealed class NetworkResult<out T> {
    class Success<out T>(val data: T?) : NetworkResult<T>()
    class Error<out T>(val code: String?, val errorMessage: String?) : NetworkResult<T>()
    class Exception<out T>(val exceptionMessage: String?) : NetworkResult<T>()
}