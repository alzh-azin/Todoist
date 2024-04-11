package com.example.todoist.project.data.network

import com.example.todoist.project.data.local.ProjectEntity
import com.example.todoist.project.domain.model.Project
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectNetwork(
    @Json(name = "child_order")
    val childOrder: Int,
    val collapsed: Boolean,
    val color: String,
    @Json(name = "created_at")
    val createdAt: String,
    val id: String,
    @Json(name = "inbox_project")
    val inboxProject: Boolean?,
    @Json(name = "is_archived")
    val isArchived: Boolean,
    @Json(name = "is_deleted")
    val isDeleted: Boolean,
    @Json(name = "is_favorite")
    val isFavorite: Boolean,
    val name: String,
    @Json(name = "parent_id")
    val parentId: String?,
    val shared: Boolean,
    @Json(name = "sync_id")
    val syncId: String?,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "v2_id")
    val v2Id: String,
    @Json(name = "view_style")
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

    fun toProjectEntity() = ProjectEntity(
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
