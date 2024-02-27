package com.example.todoist.data

import retrofit2.Response
import retrofit2.http.GET

interface ProjectService {

    @GET("projects")
    suspend fun getProjectList(): Response<ProjectListResponse>
}