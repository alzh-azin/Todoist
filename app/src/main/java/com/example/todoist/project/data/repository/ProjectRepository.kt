package com.example.todoist.project.data.repository

import com.example.todoist.core.network.utils.ConnectivityObserver
import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.project.data.local.ProjectLocalDataSource
import com.example.todoist.project.domain.model.Project
import com.example.todoist.sync.data.network.SyncRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val syncRemoteDataSource: SyncRemoteDataSource,
    private val projectLocalDataSource: ProjectLocalDataSource
) {

    fun getProjectList(): Flow<NetworkResult<List<Project>>> = flow {

        emit(NetworkResult.Success(projectLocalDataSource.getProjectList().map {
            it.toProject()
        }))

        emit(NetworkResult.Loading(true))

        if (connectivityObserver.isConnected()) {

            val projectList = syncRemoteDataSource.sync()

            if (projectList is NetworkResult.Success) {

                projectLocalDataSource.insertProjectList(
                    projectList.data?.map { projectNetwork ->
                        projectNetwork.toProjectEntity()
                    }.orEmpty()
                )

                emit(NetworkResult.Success(projectList.data?.map { projectNetwork ->
                    projectNetwork.toProject()
                }))
            }
        }
    }
}