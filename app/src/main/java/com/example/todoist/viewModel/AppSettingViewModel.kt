package com.example.todoist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.data.AuthenticationRepository
import com.example.todoist.data.LocalDataSource
import com.example.todoist.data.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AppSettingViewModel @Inject constructor(
    val localDataSource: LocalDataSource,
    val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<NetworkResult<Unit>>(NetworkResult.Loading(true))
    val loginState: StateFlow<NetworkResult<Unit>> = _loginState

//    val loginState: StateFlow<NetworkResult<Unit>> =
//        authenticationRepository.getToken()
//            .stateIn(
//                scope = viewModelScope,
//                started = SharingStarted.WhileSubscribed(),
//                initialValue = NetworkResult<Unit>
//            )


    val isLoggedIn = localDataSource.isUserLoggedIn()
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)


    fun setToken(token: String) {
        viewModelScope.launch(Dispatchers.IO) {
            localDataSource.setToken(token)
        }
    }

    fun getToken(code: String) {

        authenticationRepository.getToken(code)
            .onEach { result ->
                _loginState.value = result
            }
            .launchIn(viewModelScope)
    }
}