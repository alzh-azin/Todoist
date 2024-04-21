package com.example.todoist.sync.workers

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.CoroutineWorker
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkRequest.Companion.MIN_BACKOFF_MILLIS
import androidx.work.WorkerParameters
import com.example.todoist.sync.domain.DoSync
import com.example.todoist.sync.initializers.PERIODIC_REPEAT_INTERVAL
import com.example.todoist.sync.initializers.syncConstraints
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

@HiltWorker
class FullSyncWorker @AssistedInject constructor(
    @Assisted private val appContext: Context,
    @Assisted private val params: WorkerParameters,
    private val doSync: DoSync
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {

        return try {

            doSync()

            Result.success()
        } catch (throwable: Throwable) {

            Result.retry()
        }
    }

    companion object {

        fun startUpSyncWork(): PeriodicWorkRequest = PeriodicWorkRequestBuilder<FullSyncWorker>(
            PERIODIC_REPEAT_INTERVAL,
            TimeUnit.MILLISECONDS
        )
            .setConstraints(syncConstraints)
            .setBackoffCriteria(
                BackoffPolicy.LINEAR,
                MIN_BACKOFF_MILLIS,
                TimeUnit.MILLISECONDS
            )
            .build()

        const val FULL_SYNC_WORK_NAME = "full_sync_worker"


    }
}