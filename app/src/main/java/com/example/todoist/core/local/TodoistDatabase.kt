package com.example.todoist.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoist.project.data.local.ProjectDao
import com.example.todoist.project.data.local.ProjectEntity
import com.example.todoist.task.data.local.TaskDao
import com.example.todoist.task.data.local.TaskEntity

@Database(
    entities = [
        ProjectEntity::class,
        TaskEntity::class
    ],
    version = 1
)
abstract class TodoistDatabase : RoomDatabase() {

    abstract val projectDao: ProjectDao
    abstract val taskDao: TaskDao
}