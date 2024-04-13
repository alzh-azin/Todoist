package com.example.todoist.project.data.repository

import com.example.todoist.core.network.utils.ConnectivityObserver
import com.example.todoist.project.data.local.ProjectLocalDataSource
import com.example.todoist.project.data.local.toProjectEntity
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

    suspend fun insertProjectList(projectList: List<Project>) {
        projectLocalDataSource.insertProjectList(projectList.map { project ->
            project.toProjectEntity()
        })
    }

    fun getProjectList(): Flow<List<Project>> = flow {

        emit(projectLocalDataSource.getProjectList().map {
            it.toProject()
        })

    }
}