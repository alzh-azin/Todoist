package com.example.todoist.core.network.utils

import android.util.Log
import retrofit2.Response

suspend fun <T> safeApiCall(
    call: suspend () -> Response<T>,
    exceptionMessage: String
): NetworkResult<T> {
    return try {
        val response = call()

        if (response.isSuccessful) {
            val body = response.body()
            Log.d("NetworkTest", "safeApiCall: ${response.code()}")
            NetworkResult.Success(body)

        } else {

            val error = errorParser(response.errorBody()?.string())
            NetworkResult.Error(error.code, error.message)
        }
    } catch (e: Exception) {
        NetworkResult.Exception(exceptionMessage)
    }
}