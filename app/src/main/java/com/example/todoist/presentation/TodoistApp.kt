package com.example.todoist.presentation

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoist.Routes
import com.example.todoist.viewModel.AppSettingViewModel
import kotlinx.coroutines.CoroutineScope

@Composable
fun MainScreen(
    appSettingViewModel: AppSettingViewModel,
    onLogin: () -> Unit
) {

    Surface {

        TodoistApp(
            appSettingViewModel = appSettingViewModel,
            onLogin = onLogin
        )
    }
}


@Composable
fun TodoistApp(
    onLogin: () -> Unit,
    appSettingViewModel: AppSettingViewModel = hiltViewModel(),
    navController: NavHostController = rememberNavController(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) {

    val token by appSettingViewModel.isLoggedIn.collectAsStateWithLifecycle()

    val loginState by appSettingViewModel.loginState.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {

            }
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = Routes.Landing.route
        ) {

            composable(Routes.Landing.route) {

                token?.let {
                    if (it.isEmpty()) {
                        navController.navigate(Routes.Login.route)
                    } else {
                        navController.navigate(Routes.Home.route)
                    }
                }
            }


            composable(Routes.Login.route) {

                LoginRoute(
                    token = token,
                    onLogin = onLogin,
                    navController = navController,
                    loginState = loginState
                )

            }

            composable(Routes.Home.route) {
                Text(text = "This is home")
            }
        }

    }


}