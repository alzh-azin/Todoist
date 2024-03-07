package com.example.todoist.project.ui.model

import com.example.todoist.project.domain.model.Project

data class ProjectView(
    val id: String,
    val name: String,
    val commentCount: Int
)

fun Project.toProjectView() = ProjectView(
    id = id,
    name = name,
    commentCount = commentCount
)
