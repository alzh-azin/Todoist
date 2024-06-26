package com.example.todoist.sync.data.network

import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.core.network.utils.safeApiCall
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRemoteDataSource @Inject constructor(
    private val syncService: SyncService
) {

    suspend fun sync(): NetworkResult<SyncNetwork> = safeApiCall(
        call = {
            syncService.sync(
                resourceTypes = listOf(
                    SyncResourceTypeHelper.projects.value,
                    SyncResourceTypeHelper.tasks.value
                ).toJsonString()
            )
        },
        exceptionMessage = "Error syncing data"
    )
}