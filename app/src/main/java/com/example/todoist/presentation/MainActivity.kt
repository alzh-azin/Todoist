package com.example.todoist.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todoist.Routes
import com.example.todoist.ui.theme.TodoistTheme
import com.example.todoist.viewModel.AppSettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoistTheme {

                navController = rememberNavController()

                val appSettingViewModel: AppSettingViewModel = hiltViewModel()

                val onLogin = {
                    val loginIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://todoist.com/oauth/authorize?client_id=766ef02efd6b42429cc7a69f3e35bd3a&scope=data:read,data:delete&state=secretstring")
                    )

                    appSettingViewModel.setToken("aaaaaaaaa")
                    startActivity(loginIntent)
                }

                TodoistApp(
                    navController = navController,
                    onLogin = onLogin
                )

            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()


        val uri: Uri? = intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                navController.navigate(Routes.Home.route)
            } else if ((uri.getQueryParameter("error")) != null) {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

