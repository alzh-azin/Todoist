package com.example.todoist.task.data.network

import com.example.todoist.task.domain.model.Task
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TaskNetwork(
    @Json(name = "added_at")
    val addedAt: String,
    @Json(name = "added_by_uid")
    val addedByUid: String,
    @Json(name = "assigned_by_uid")
    val assignedByUid: String?,
    val checked: Boolean,
    @Json(name = "child_order")
    val childOrder: Int,
    val collapsed: Boolean,
    @Json(name = "completed_at")
    val completedAt: String?,
    val content: String,
    @Json(name = "day_order")
    val dayOrder: Int,
    val description: String,
    val due: String?,
    val duration: String?,
    val id: String,
    @Json(name = "is_deleted")
    val isDeleted: Boolean,
    @Json(name = "parent_id")
    val parentId: String?,
    val priority: Int,
    @Json(name = "project_id")
    val projectId: String,
    @Json(name = "responsible_uid")
    val responsibleUid: String?,
    @Json(name = "section_id")
    val sectionId: String?,
    @Json(name = "sync_id")
    val syncId: String?,
    @Json(name = "updated_at")
    val updatedAt: String,
    @Json(name = "user_id")
    val userId: String,
    @Json(name = "v2_id")
    val v2Id: String,
    @Json(name = "v2_parent_id")
    val v2ParentId: String?,
    @Json(name = "v2_project_id")
    val v2ProjectId: String,
    @Json(name = "v2_section_id")
    val v2SectionId: String?
)

fun TaskNetwork.toTask() = Task(
    addedAt = addedAt,
    addedByUid = addedByUid,
    assignedByUid = assignedByUid,
    checked = checked,
    childOrder = childOrder,
    collapsed = collapsed,
    completedAt = completedAt,
    content = content,
    dayOrder = dayOrder,
    description = description,
    due = due,
    duration = duration,
    id = id,
    isDeleted = isDeleted,
    parentId = parentId,
    priority = priority,
    projectId = projectId,
    responsibleUid = responsibleUid,
    sectionId = sectionId,
    syncId = syncId,
    updatedAt = updatedAt,
    userId = userId,
    v2Id = v2Id,
    v2ParentId = v2ParentId,
    v2ProjectId = v2ProjectId,
    v2SectionId = v2SectionId
)

