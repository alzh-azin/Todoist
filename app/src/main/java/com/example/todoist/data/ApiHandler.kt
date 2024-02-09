package com.example.todoist.data

import android.util.Log
import org.json.JSONObject
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

fun errorParser(errorBody: String?): TodoistError {
    try {
        return if (errorBody != null) {

            val errorBodyObject = JSONObject(errorBody)

            val errorCode = if (errorBodyObject.has("error")) {
                if (errorBodyObject.getJSONObject("error").has("code"))
                    errorBodyObject.getJSONObject("error").getString("code")
                else ""
            } else ""

            val errorMessage = errorBodyObject.getJSONObject("error").toString()

            TodoistError(
                code = errorCode,
                message = errorMessage
            )
        } else {
            TodoistError()
        }
    } catch (e: Exception) {
        return TodoistError()
    }
}
