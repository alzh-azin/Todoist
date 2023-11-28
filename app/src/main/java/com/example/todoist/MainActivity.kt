package com.example.todoist

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.todoist.ui.theme.TodoistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoistTheme {
//                val intent = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://todoist.com/oauth/authorize?client_id=766ef02efd6b42429cc7a69f3e35bd3a&scope=data:read,data:delete&state=secretstring")
//                )
//
//                Button(onClick = {
//                    startActivity(intent)
//
//                }) {
//                    Text(text = "Click on me")
//                }

                var showWebView by remember { mutableStateOf(false) }
                Button(onClick = {

                    showWebView = true

                }) {
                    Text(text = "Click on me")

                    if (showWebView) {
                        AndroidView(
                            factory = { context ->
                                WebView(context).apply {
                                    webViewClient = WebViewClient()
                                    loadUrl("https://todoist.com/oauth/authorize?client_id=766ef02efd6b42429cc7a69f3e35bd3a&scope=data:read,data:delete&state=secretstring")
                                }
                            },
                            update = { webView ->
                                // No-op for now
                            },
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        val uri: Uri? = intent?.data
        if (uri != null) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                Log.d("AuthTestCode", "$code")
                Toast.makeText(this, "Login success!", Toast.LENGTH_SHORT).show()
            } else if ((uri.getQueryParameter("error")) != null) {
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TodoistTheme {
        Greeting("Android")
    }
}