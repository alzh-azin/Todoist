package com.example.todoist.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.data.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppSettingViewModel @Inject constructor(
    val localDataSource: LocalDataSource
) : ViewModel() {


    val authenticationState: StateFlow<String?> = localDataSource.authToken
        .flowOn(Dispatchers.Main)
        .map { token ->
            token
        }
        .catch { exception ->
            Log.d("DataStoreException", "${exception.message}")

        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initialValue = null)


    fun setToken(token: String) {
        viewModelScope.launch {
            localDataSource.setToken(token)
        }
    }

}