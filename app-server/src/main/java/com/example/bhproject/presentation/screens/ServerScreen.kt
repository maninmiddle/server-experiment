package com.example.bhproject.presentation.screens

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.bhproject.services.WebSocketService

@Composable
fun ServerScreen(viewModel: ServerScreenViewModel = hiltViewModel()) {
    var isRunning by remember {
        mutableStateOf(false)
    }
    var port by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    var logText by remember {
        mutableStateOf("")
    }
    var portVisible by remember {
        mutableStateOf(false)
    }

    val logs by viewModel.logs.collectAsState()
    LaunchedEffect(key1 = logs) {
        logText = logs.joinToString("\n") { it.message }
    }

    Column {
        Button(onClick = { portVisible = !portVisible }) {
            Text(text = "Config")
        }

        if (portVisible) {
            TextField(value = port, onValueChange = {
                port = it
            },
                label = { Text(text = port) })
        }

        Button(onClick = {
            isRunning = !isRunning
            val service = Intent(context, WebSocketService::class.java)
            if (isRunning) {
                context.startService(service)
            } else {
                context.stopService(service)
            }
        }) {
            Text(text = if (isRunning) "Выключить" else "Включить")
        }

        Button(onClick = {
            viewModel.getLogs()
        }) {
            Text(text = "Логи")
        }

        Text(text = logText)

    }
}
