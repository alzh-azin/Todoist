package com.example.todoist.task.data.local

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskLocalDataSource @Inject constructor(
    private val taskDao: TaskDao
) {

    suspend fun getTasksByProjectId(projectId: String) = taskDao.getTasksByProjectId(projectId)

    suspend fun insertTaskList(tasks: List<TaskEntity>) = taskDao.insertTaskList(tasks)
}