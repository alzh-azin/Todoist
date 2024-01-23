package com.example.todoist

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoist.ui.theme.TodoistTheme
import dagger.hilt.android.AndroidEntryPoint


fun openAudioSelector(): Intent {
    val intent = Intent()
    intent.action = Intent.ACTION_GET_CONTENT
    intent.type = "audio/*"
    val mimetypes = arrayOf("audio/mpeg")
    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimetypes)
    return intent
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("AuthTestCode", "onCreate")

        setContent {
            TodoistTheme {
                navController = rememberNavController()

//                Button(
//                    modifier = Modifier.padding(top = 100.dp),
//                    onClick = {
//
//                        val intent = Intent(
//                            Intent.ACTION_VIEW,
//                            Uri.parse("https://todoist.com/oauth/authorize?client_id=766ef02efd6b42429cc7a69f3e35bd3a&scope=data:read,data:delete&state=secretstring")
//                        )
//
//                        startActivity(intent)
//
//                    },
//
//                ) {
//                    Text(text = "Get Started")
//                }


                NavHost(
                    navController = navController,
                    startDestination = Routes.Login.route
                ) {

                    composable(Routes.Login.route) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,

                            ) {
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxSize()
                                    .wrapContentSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.at_work),
                                    contentDescription = null,
                                )

                                Text(
                                    text = "Todoist",
                                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                                )

                                Text(
                                    text = "Designed to help you better manage your work",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = Color.Gray
                                )
                            }

                            Spacer(modifier = Modifier.height(56.dp))

                            Button(
                                onClick = {

                                    val input = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse("https://todoist.com/oauth/authorize?client_id=766ef02efd6b42429cc7a69f3e35bd3a&scope=data:read,data:delete&state=secretstring")
                                    )

                                    startActivity(input)
                                },
                                modifier = Modifier
                                    .padding(bottom = 32.dp)

                            ) {
                                Text(text = "Get Started")
                            }
                        }

                    }

                    composable(Routes.Home.route) {
                        Text(text = "This is home")
                    }
                }
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
                Log.d("AuthTestCode", "$code")
                Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()
                navController.navigate(Routes.Home.route)
            } else if ((uri.getQueryParameter("error")) != null) {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }


}

