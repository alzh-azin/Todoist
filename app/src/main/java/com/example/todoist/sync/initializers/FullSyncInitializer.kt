package com.example.todoist.sync.initializers

import android.content.Context
import androidx.work.ExistingWorkPolicy
import androidx.work.WorkManager
import com.example.todoist.sync.workers.FullSyncWorker
import com.example.todoist.sync.workers.FullSyncWorker.Companion.FULL_SYNC_WORK_NAME

object FullSyncInitializer {

    fun initialize(context: Context) {
        WorkManager.getInstance(context).apply {
            enqueueUniqueWork(
                FULL_SYNC_WORK_NAME,
                ExistingWorkPolicy.KEEP,
                FullSyncWorker.startUpSyncWork(),
            )
        }
    }
}

