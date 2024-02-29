package com.example.todoist.home.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
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

    //TODO return Boolean
    fun isUserLoggedIn(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { pref ->
            pref[USER_TOKEN_KEY] ?: ""
        }
    }

    suspend fun setToken(token: String) {
        dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = token
        }
    }

    companion object {
         val USER_TOKEN_KEY = stringPreferencesKey("user-token")
    }

}