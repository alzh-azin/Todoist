package com.example.todoist.core

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoist.core.TodoistConstants.USER_PREFERENCES_NAME

val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)


