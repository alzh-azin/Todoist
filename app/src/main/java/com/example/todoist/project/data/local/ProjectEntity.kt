package com.example.todoist.project.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.todoist.project.domain.model.Project

@Entity
data class ProjectEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val commentCount: Int
) {

    fun toProject() = Project(
        id = id,
        name = name,
        commentCount = commentCount
    )
}
