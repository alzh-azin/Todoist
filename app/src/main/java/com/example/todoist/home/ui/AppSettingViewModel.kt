package com.example.todoist.home.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoist.authentication.domain.CheckIfUserLoggedIn
import com.example.todoist.authentication.domain.GetToken
import com.example.todoist.authentication.ui.LoginState
import com.example.todoist.core.network.utils.NetworkResult
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
    private val checkIfUserLoggedIn: CheckIfUserLoggedIn,
    private val getToken: GetToken
) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginState?>(null)
    val loginState: StateFlow<LoginState?> = _loginState


    val isLoggedIn = checkIfUserLoggedIn()
        .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = null)


    fun sendToken(code: String) {

        getToken(code)
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