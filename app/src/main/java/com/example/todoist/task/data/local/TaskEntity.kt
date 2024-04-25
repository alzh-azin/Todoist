package com.example.todoist.task.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoist.task.domain.model.Task

@Entity
data class TaskEntity(
    val addedAt: String,
    val addedByUid: String,
    val assignedByUid: String?,
    val checked: Boolean,
    val childOrder: Int,
    val collapsed: Boolean,
    val completedAt: String?,
    val content: String,
    val dayOrder: Int,
    val description: String,
    val due: String?,
    val duration: String?,
    @PrimaryKey
    val id: String,
    val isDeleted: Boolean,
    val parentId: String?,
    val priority: Int,
    val projectId: String,
    val responsibleUid: String?,
    val sectionId: String?,
    val syncId: String?,
    val updatedAt: String,
    val userId: String,
    val v2Id: String,
    val v2ParentId: String?,
    val v2ProjectId: String,
    val v2SectionId: String?
)

fun TaskEntity.toTask() = Task(
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

fun Task.toTaskEntity() = TaskEntity(
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