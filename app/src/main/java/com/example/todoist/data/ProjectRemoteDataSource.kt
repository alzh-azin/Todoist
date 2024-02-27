package com.example.todoist.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRemoteDataSource @Inject constructor(
    private val projectService: ProjectService
) {

    suspend fun getProjectList(): NetworkResult<ProjectListResponse> = safeApiCall(
        call = {
            projectService.getProjectList()
        },
        exceptionMessage = "Error getting project list"
    )
}