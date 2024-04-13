package com.example.todoist.home.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoist.Routes
import com.example.todoist.authentication.ui.LocalFullSyncInitializer
import com.example.todoist.authentication.ui.LoginRoute
import com.example.todoist.project.ui.ProjectViewModel
import com.example.todoist.project.ui.model.ProjectView
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

    val projectViewModel: ProjectViewModel = hiltViewModel()

    val state by projectViewModel.projectState.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                ProjectList(state.projects)
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

                val fullSyncInitializer = LocalFullSyncInitializer.current
                LaunchedEffect(key1 = Unit) {

                    fullSyncInitializer.initialize()

                    Log.d("TestHomeWorkManager", "WorkManager initialized")
                }

                projectViewModel.getProjectList()
            }
        }

    }


}

@Composable
fun ProjectList(
    projects: List<ProjectView>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(
            items = projects,
            key = { project ->
                project.id
            }
        ) { project ->
            ProjectItem(name = project.name)
        }
    }
}

@Composable
fun ProjectItem(
    name: String,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(16.dp)) {
        Text(text = name)
    }
}