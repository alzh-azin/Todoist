package com.example.todoist.sync.domain.model

import com.example.todoist.project.domain.model.Project
import com.example.todoist.task.domain.model.Task

data class Sync(
    val projects: List<Project>,
    val tasks: List<Task>
)