package com.example.todoist.sync.initializers

import androidx.work.Constraints
import androidx.work.NetworkType

val syncConstraints
    get() = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

const val PERIODIC_REPEAT_INTERVAL = 1000L