package com.example.todoist.task.data.repository

import com.example.todoist.task.data.local.TaskEntity
import com.example.todoist.task.data.local.TaskLocalDataSource
import com.example.todoist.task.data.local.toTask
import com.example.todoist.task.data.local.toTaskEntity
import com.example.todoist.task.domain.model.Task
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(
    private val taskLocalDataSource: TaskLocalDataSource
) {

    suspend fun getTasksByProjectId(projectId: String) =
        taskLocalDataSource.getTasksByProjectId(projectId)
            .map(TaskEntity::toTask)

    suspend fun insertTaskList(tasks: List<Task>) =
        taskLocalDataSource.insertTaskList(tasks.map(Task::toTaskEntity))

}