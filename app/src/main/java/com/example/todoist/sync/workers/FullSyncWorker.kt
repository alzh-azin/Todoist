package com.example.todoist.sync.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkerParameters
import com.example.todoist.core.network.utils.NetworkResult
import com.example.todoist.sync.domain.DoSync
import com.example.todoist.sync.initializers.syncConstraints

class FullSyncWorker(
    private val appContext: Context,
    private val params: WorkerParameters,
    private val doSync: DoSync
) : CoroutineWorker(appContext, params) {
    override suspend fun doWork(): Result {

        Log.d("TestHomeWorkManager", "doWork")

        return when (doSync()) {
            is NetworkResult.Success -> Result.success()
            else -> Result.retry()
        }
    }

    companion object {

        fun startUpSyncWork(): OneTimeWorkRequest = OneTimeWorkRequestBuilder<FullSyncWorker>()
//            .setExpedited(OutOfQuotaPolicy.RUN_AS_NON_EXPEDITED_WORK_REQUEST)
            .setConstraints(syncConstraints)
//            .setInitialDelay(INITIAL_DURATION, TimeUnit.MILLISECONDS)
//            .setBackoffCriteria(
//                BackoffPolicy.LINEAR,
//                MIN_BACKOFF_MILLIS,
//                TimeUnit.MILLISECONDS
//            )
            .build()

        const val FULL_SYNC_WORK_NAME = "full_sync_worker"


    }
}