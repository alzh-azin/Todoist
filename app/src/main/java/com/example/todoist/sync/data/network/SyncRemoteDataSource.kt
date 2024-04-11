package com.example.todoist.sync.data.network

import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.core.network.utils.safeApiCall
import com.example.todoist.project.data.network.ProjectNetwork
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRemoteDataSource @Inject constructor(
    private val syncService: SyncService
) {

    suspend fun sync(): NetworkResult<List<ProjectNetwork>> = safeApiCall(
        call = {
            syncService.getProjectList()
        },
        exceptionMessage = "Error syncing data"
    )
}