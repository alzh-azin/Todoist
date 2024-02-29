package com.example.todoist.project.data.repository

import com.example.todoist.core.network.utils.ConnectivityObserver
import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.project.data.network.ProjectNetwork
import com.example.todoist.project.data.network.ProjectRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val projectRemoteDataSource: ProjectRemoteDataSource
) {

    fun getProjectList(): Flow<NetworkResult<List<ProjectNetwork>>> = flow {

        emit(NetworkResult.Loading(true))

        if (connectivityObserver.isConnected()) {

            val projectList = projectRemoteDataSource.getProjectList()

            if (projectList is NetworkResult.Success) {
                emit(NetworkResult.Success(projectList.data))
            }
        }
    }
}