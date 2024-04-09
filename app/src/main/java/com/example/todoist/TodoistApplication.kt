package com.example.todoist

import android.app.Application
import android.content.res.Configuration
import com.example.todoist.sync.initializers.FullSyncInitializer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TodoistApplication : Application() {

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        FullSyncInitializer.initialize(this)
    }
}