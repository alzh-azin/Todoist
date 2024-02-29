package com.example.todoist.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectNetwork(
    val id: String,
    val name: String,
    @Json(name = "comment_count")
    val commentCount: Int
)
