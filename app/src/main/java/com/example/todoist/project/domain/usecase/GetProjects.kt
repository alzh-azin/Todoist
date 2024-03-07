package com.example.todoist.project.domain.usecase

import com.example.todoist.project.data.repository.ProjectRepository
import javax.inject.Inject

class GetProjects @Inject constructor(private val projectRepository: ProjectRepository) {
    operator fun invoke() = projectRepository.getProjectList()
}