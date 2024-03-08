package com.example.todoist.project.data.network

import com.example.todoist.project.data.local.ProjectEntity
import com.example.todoist.project.domain.model.Project
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectNetwork(
    val id: String,
    val name: String,
    @Json(name = "comment_count")
    val commentCount: Int
) {

    fun toProject() = Project(
        id = id,
        name = name,
        commentCount = commentCount
    )

    fun toProjectEntity() = ProjectEntity(
        id = id,
        name = name,
        commentCount = commentCount
    )
}
