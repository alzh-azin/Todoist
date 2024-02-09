package com.example.todoist.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.todoist.ui.theme.TodoistTheme
import com.example.todoist.viewModel.AppSettingViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val appSettingsViewModel: AppSettingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TodoistTheme {

                val onLogin = {
                    val loginIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://todoist.com/oauth/authorize?client_id=766ef02efd6b42429cc7a69f3e35bd3a&scope=data:read,data:delete&state=secretstring")
                    )

                    startActivity(loginIntent)
                }


                MainScreen(
                    appSettingViewModel = appSettingsViewModel,
                    onLogin = onLogin
                )
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri: Uri? = intent?.data

        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {

                Log.d("AuthCode", "$code")

                appSettingsViewModel.getToken(code)

            } else if ((uri.getQueryParameter("error")) != null) {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

