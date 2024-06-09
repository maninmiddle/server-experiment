package com.example.app_client.presentation.screens

import android.content.Context.ACCESSIBILITY_SERVICE
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.getSystemService
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.app_client.domain.model.Log
import com.example.app_client.services.ClientAccessibilityService

@Composable
fun ClientScreen(viewModel: ClientViewModel = hiltViewModel()) {
    var ip by remember {
        mutableStateOf("")
    }
    var port by remember {
        mutableStateOf("")
    }
    var isRunning by remember {
        mutableStateOf(false)
    }

    Column {
        TextField(value = ip, onValueChange = { ip = it }, label = { Text(text = "Server IP") })
        TextField(value = port, onValueChange = { port = it }, label = { Text(text = "Port") })
        Button(onClick = {
            if (isRunning) {
                viewModel.stopClient()
            } else {
                viewModel.startClient("127.0.0.1", 8080)
                //viewModel.openChrome("https://www.autodraw.com")
                viewModel.sendMessage(Log("Chrome is Open"))
            }
            isRunning = !isRunning
        }) {
            Text(text = if (isRunning) "Stop" else "Start")
        }


        Button(onClick = { viewModel.sendMessage(Log("test")) }) {
            Text(text = "Send test")
        }
    }
}