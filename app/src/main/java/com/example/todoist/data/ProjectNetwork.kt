package com.example.todoist.data

import com.squareup.moshi.Json

data class ProjectNetwork(
    val id: String,
    val name: String,
    @Json(name = "comment_count")
    val commentCount: Int
)
