package com.example.todoist.core.network.utils

import org.json.JSONObject

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
