package com.example.todoist.project.data.network

import com.example.todoist.core.network.utils.Result
import com.example.todoist.core.network.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRemoteDataSource @Inject constructor(
    private val projectService: ProjectService
) {

    suspend fun getProjectList(): Result<List<ProjectNetwork>> = safeApiCall(
        call = {
            projectService.getProjectList()
        },
        exceptionMessage = "Error getting project list"
    )
}