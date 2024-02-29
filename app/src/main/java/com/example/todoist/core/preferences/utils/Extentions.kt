package com.example.todoist.core.preferences.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.todoist.core.utils.TodoistConstants.USER_PREFERENCES_NAME

val Context.dataStore by preferencesDataStore(
    name = USER_PREFERENCES_NAME
)


