package com.example.todoist.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TodoistError(
    var code: String? = null,
    val message: String? = null
)
