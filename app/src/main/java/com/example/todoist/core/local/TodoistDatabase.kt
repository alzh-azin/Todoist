package com.example.todoist.core.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoist.project.data.local.ProjectDao
import com.example.todoist.project.data.local.ProjectEntity

@Database(
    entities = [ProjectEntity::class],
    version = 1
)
abstract class TodoistDatabase : RoomDatabase() {

    abstract val projectDao: ProjectDao
}