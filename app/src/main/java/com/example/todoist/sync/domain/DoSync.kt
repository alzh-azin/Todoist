package com.example.todoist.sync.domain

import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.project.domain.usecase.InsertProjectList
import com.example.todoist.sync.data.repository.SyncRepository
import javax.inject.Inject

class DoSync @Inject constructor(
    private val syncRepository: SyncRepository,
    private val insertProjectList: InsertProjectList
) {
    suspend operator fun invoke(): NetworkResult<Unit> {
        return when (val syncResult = syncRepository.sync()) {
            is NetworkResult.Success -> {

                insertProjectList(syncResult.data?.projects.orEmpty())
                NetworkResult.Success(Unit)
            }

            is NetworkResult.Error -> NetworkResult.Error(errorMessage = "Something went wrong, please try again")
            else -> NetworkResult.Loading(true)
        }
    }
}
