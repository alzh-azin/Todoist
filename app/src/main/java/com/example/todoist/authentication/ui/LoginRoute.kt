package com.example.todoist.authentication.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

            val fullSyncInitializer = LocalFullSyncInitializer.current
            LaunchedEffect(key1 = loginState) {

                fullSyncInitializer.initialize()
            }
            navController.navigate(Routes.Home.route)

        }

        is LoginState.Error -> {

        }

        else -> {

        }
    }
}