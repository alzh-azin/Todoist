package com.example.todoist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.data.AuthenticationRepository
import com.example.todoist.data.LocalDataSource
import com.example.todoist.data.NetworkResult
import com.example.todoist.presentation.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject


@HiltViewModel
class AppSettingViewModel @Inject constructor(
    val localDataSource: LocalDataSource,
    val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState?>(null)
    val loginState: StateFlow<LoginState?> = _loginState


    val isLoggedIn = localDataSource.isUserLoggedIn()
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)


    fun getToken(code: String) {

        authenticationRepository.getToken(code)
            .onEach { result ->
                _loginState.value = when (result) {

                    is NetworkResult.Success -> {
                        LoginState.Success
                    }

                    is NetworkResult.Error -> {
                        LoginState.Error(result.errorMessage.orEmpty())
                    }

                    is NetworkResult.Loading -> {
                        LoginState.Loading
                    }

                    is NetworkResult.Exception -> {
                        LoginState.Error(result.exceptionMessage.orEmpty())
                    }
                }
            }
            .launchIn(viewModelScope)
    }
}