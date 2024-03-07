package com.example.todoist.project.ui

import com.example.todoist.project.ui.model.ProjectView

data class ProjectState(
    val projects: List<ProjectView> = mutableListOf()
)
