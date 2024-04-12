package com.example.todoist.authentication.ui

import androidx.compose.runtime.staticCompositionLocalOf
import com.example.todoist.sync.initializers.FullSyncInitializer

val LocalFullSyncInitializer = staticCompositionLocalOf<FullSyncInitializer> {
    error("No FullSyncInitializer provided")
}