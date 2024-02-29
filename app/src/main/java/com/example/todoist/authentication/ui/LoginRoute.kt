package com.example.todoist.authentication.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoist.Routes

@Composable
fun LoginRoute(
    token: String?,
    loginState: LoginState?,
    navController: NavHostController = rememberNavController(),
    onLogin: () -> Unit
) {

    LoginScreen(onLogin)

    when (loginState) {

        is LoginState.Loading -> {
            LoginLoadingDialog()
        }

        is LoginState.Success -> {
            navController.navigate(Routes.Home.route)

        }

        is LoginState.Error -> {

        }

        else -> {

        }
    }
}