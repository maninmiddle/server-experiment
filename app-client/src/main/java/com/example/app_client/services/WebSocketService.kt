package com.example.app_client.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.app_client.data.network.WebSocketClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WebSocketService: Service() {
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.IO + serviceJob)

    private lateinit var webSocketClient: WebSocketClient

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        webSocketClient = WebSocketClient()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceScope.launch {
            webSocketClient.connect()
            webSocketClient.receiveMessage().collect {message ->
            }
        }
        return START_STICKY

    }

}