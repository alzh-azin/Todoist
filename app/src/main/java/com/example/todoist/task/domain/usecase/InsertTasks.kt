package com.example.todoist.task.domain.usecase

import com.example.todoist.task.data.repository.TaskRepository
import com.example.todoist.task.domain.model.Task
import javax.inject.Inject

class InsertTaskList @Inject constructor(
    private val taskRepository: TaskRepository
) {

    suspend operator fun invoke(tasks: List<Task>) {
        taskRepository.insertTaskList(tasks)
    }
}