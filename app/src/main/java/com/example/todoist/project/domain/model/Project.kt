package com.example.todoist.project.domain.model

data class Project(
    val childOrder: Int,
    val collapsed: Boolean,
    val color: String,
    val createdAt: String,
    val id: String,
    val inboxProject: Boolean?,
    val isArchived: Boolean,
    val isDeleted: Boolean,
    val isFavorite: Boolean,
    val name: String,
    val parentId: String?,
    val shared: Boolean,
    val syncId: String?,
    val updatedAt: String,
    val v2Id: String,
    val viewStyle: String

)
