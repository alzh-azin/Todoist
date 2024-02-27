package com.example.todoist.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val projectRemoteDataSource: ProjectRemoteDataSource
) {

    fun getProjectList(): Flow<NetworkResult<ProjectListResponse>> = flow {

        emit(NetworkResult.Loading(true))

        if (connectivityObserver.isConnected()) {

            val projectList = projectRemoteDataSource.getProjectList()

            if (projectList is NetworkResult.Success) {
                emit(projectList)
            }
        }
    }
}