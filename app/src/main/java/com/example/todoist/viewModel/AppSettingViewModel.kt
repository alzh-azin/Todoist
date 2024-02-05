package com.example.todoist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.data.LocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppSettingViewModel @Inject constructor(
    val localDataSource: LocalDataSource
) : ViewModel() {

    val isLoggedIn = localDataSource.isUserLoggedIn()
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)


    fun setToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.setToken(token)
        }
    }

}