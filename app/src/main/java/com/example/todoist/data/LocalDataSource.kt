package com.example.todoist.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val authToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[USER_TOKEN_KEY] ?: ""
        }.catch { exception ->
            Log.d("DataStoreException", "${exception.message}")
        }

    suspend fun setToken(token: String) {
        Log.d("TestToken", "setToken: ${token}")
        dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    companion object {
        private val USER_TOKEN_KEY = stringPreferencesKey("user-token")
    }

}