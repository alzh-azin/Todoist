package com.example.todoist.sync.data.repository

import com.example.todoist.core.network.utils.ConnectivityObserver
import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.project.domain.model.Project
import com.example.todoist.sync.data.network.SyncRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRepository @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val syncRemoteDataSource: SyncRemoteDataSource
) {

    suspend fun sync(): NetworkResult<List<Project>> {

        return if (connectivityObserver.isConnected()) {

            when (val projectList = syncRemoteDataSource.sync()) {
                is NetworkResult.Success -> NetworkResult.Success(projectList.data?.map {
                    it.toProject()
                })

                is NetworkResult.Error -> NetworkResult.Error(errorMessage = "Something went wrong, please try again")

                else -> NetworkResult.Loading(true)
            }
        } else {
            NetworkResult.Error(errorMessage = "No internet connection")
        }
    }

}