package com.example.todoist.authentication.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.todoist.R

@Composable
fun LoginScreen(
    onLogin: () -> Unit
) {

    val currentOnLogin by rememberUpdatedState(newValue = onLogin)

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

                currentOnLogin.invoke()
            },
            modifier = Modifier
                .padding(bottom = 32.dp)

        ) {
            Text(text = "Get Started")
        }
    }

}

@Composable
fun LoginLoadingDialog() {
    AlertDialog(
        onDismissRequest = {},
        title = { },
        text = { Text("Please wait while we process your request.") },
        confirmButton = { }
    )
}
