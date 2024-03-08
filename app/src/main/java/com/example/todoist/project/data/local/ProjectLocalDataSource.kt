package com.example.todoist.project.data.local

import androidx.room.withTransaction
import com.example.todoist.core.local.TodoistDatabase
import javax.inject.Inject

class ProjectLocalDataSource @Inject constructor(
    private val projectDao: ProjectDao,
    private val todoistDatabase: TodoistDatabase
) {

    suspend fun insertProjectList(projects: List<ProjectEntity>) {
        todoistDatabase.withTransaction {
            projectDao.deleteAllProjects()
            projectDao.insertProjectList(projects)
        }
    }


    suspend fun getProjectList() = projectDao.getProjectList()
}