package com.example.todoist.sync.domain

import com.example.todoist.sync.data.repository.SyncRepository
import javax.inject.Inject

class DoSync @Inject constructor(private val syncRepository: SyncRepository) {
    suspend operator fun invoke() = syncRepository.sync()
}
