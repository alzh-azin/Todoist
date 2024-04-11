package com.example.todoist.sync.data.network

import com.example.todoist.project.data.network.ProjectNetwork
import retrofit2.Response
import retrofit2.http.GET

interface SyncService {

    @GET("projects")
    suspend fun getProjectList(): Response<List<ProjectNetwork>>
}