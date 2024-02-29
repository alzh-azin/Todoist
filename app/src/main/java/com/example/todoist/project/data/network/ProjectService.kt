package com.example.todoist.project.data.network

import retrofit2.Response
import retrofit2.http.GET

interface ProjectService {

    @GET("projects")
    suspend fun getProjectList(): Response<List<ProjectNetwork>>
}