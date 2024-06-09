package com.example.app_client.data.network

import com.example.app_client.domain.model.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.websocket.WebSockets
import io.ktor.client.plugins.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.close
import io.ktor.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ApiService @Inject constructor() {
    private var webSocketSession: WebSocketSession? = null

    suspend fun connect(ip: String, port: Int) {
        val client = HttpClient(CIO) {
            install(WebSockets)
        }

        webSocketSession = client.webSocketSession {
            url("ws://$ip:$port/echo")
        }
    }

    suspend fun disconnect() {
        webSocketSession?.close()
        webSocketSession = null
    }

    suspend fun sendMessage(log: Log) {
        webSocketSession?.send(Frame.Text(log.message))
    }

    fun receiveMessage(): Flow<String> = flow {
        webSocketSession?.let { session ->
            for (frame in session.incoming) {
                if (frame is Frame.Text) {
                    emit(frame.readText())
                }
            }
        }
    }
}