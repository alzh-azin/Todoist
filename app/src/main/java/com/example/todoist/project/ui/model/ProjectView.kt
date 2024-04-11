package com.example.todoist.project.ui.model

import com.example.todoist.project.domain.model.Project

data class ProjectView(
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

fun Project.toProjectView() = ProjectView(
    childOrder = childOrder,
    collapsed = collapsed,
    color = color,
    createdAt = createdAt,
    id = id,
    inboxProject = inboxProject,
    isArchived = isArchived,
    isDeleted = isDeleted,
    isFavorite = isFavorite,
    name = name,
    parentId = parentId,
    shared = shared,
    syncId = syncId,
    updatedAt = updatedAt,
    v2Id = v2Id,
    viewStyle = viewStyle
)
