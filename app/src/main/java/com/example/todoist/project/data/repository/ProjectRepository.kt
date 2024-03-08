package com.example.todoist.project.data.repository

import com.example.todoist.core.network.utils.ConnectivityObserver
import com.example.todoist.core.network.utils.Result
import com.example.todoist.project.data.local.ProjectLocalDataSource
import com.example.todoist.project.data.network.ProjectRemoteDataSource
import com.example.todoist.project.domain.model.Project
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
    private val connectivityObserver: ConnectivityObserver,
    private val projectRemoteDataSource: ProjectRemoteDataSource,
    private val projectLocalDataSource: ProjectLocalDataSource
) {

    fun getProjectList(): Flow<Result<List<Project>>> = flow {

        emit(Result.Success(projectLocalDataSource.getProjectList().map {
            it.toProject()
        }))

        emit(Result.Loading(true))

        if (connectivityObserver.isConnected()) {

            val projectList = projectRemoteDataSource.getProjectList()

            if (projectList is Result.Success) {

                projectLocalDataSource.insertProjectList(
                    projectList.data?.map { projectNetwork ->
                        projectNetwork.toProjectEntity()
                    }.orEmpty()
                )

                emit(Result.Success(projectList.data?.map { projectNetwork ->
                    projectNetwork.toProject()
                }))
            }
        }
    }
}