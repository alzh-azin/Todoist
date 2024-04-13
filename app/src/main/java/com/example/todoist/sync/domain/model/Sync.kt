package com.example.todoist.sync.domain.model

import com.example.todoist.project.domain.model.Project

data class Sync(
    val projects: List<Project>
)