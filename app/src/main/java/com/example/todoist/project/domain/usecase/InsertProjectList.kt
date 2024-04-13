package com.example.todoist.project.domain.usecase

import com.example.todoist.project.data.repository.ProjectRepository
import com.example.todoist.project.domain.model.Project
import javax.inject.Inject

class InsertProjectList @Inject constructor(private val projectRepository: ProjectRepository) {
    suspend operator fun invoke(projectList: List<Project>) {
        projectRepository.insertProjectList(projectList)
    }
}