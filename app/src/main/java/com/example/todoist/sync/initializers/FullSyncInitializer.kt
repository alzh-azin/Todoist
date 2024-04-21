package com.example.todoist.sync.initializers

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.example.todoist.sync.workers.FullSyncWorker
import com.example.todoist.sync.workers.FullSyncWorker.Companion.FULL_SYNC_WORK_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FullSyncInitializer @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun initialize() {

        WorkManager.getInstance(context).apply {
            enqueueUniquePeriodicWork(
                FULL_SYNC_WORK_NAME,
                ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE,
                FullSyncWorker.startUpSyncWork(),
            )
        }
    }
}

