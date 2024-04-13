package com.example.todoist.project.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoist.project.domain.model.Project

@Entity
data class ProjectEntity(
    val childOrder: Int,
    val collapsed: Boolean,
    val color: String,
    val createdAt: String,
    @PrimaryKey
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
) {

    fun toProject() = Project(
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
}

fun Project.toProjectEntity() = ProjectEntity(
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
