package com.example.bhproject.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.bhproject.data.db.LogEntity
import com.example.bhproject.data.db.dao.LogsDao
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.netty.NettyApplicationEngine
import io.ktor.server.routing.routing
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import io.ktor.server.websocket.timeout
import io.ktor.server.websocket.webSocket
import io.ktor.websocket.Frame
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration

@AndroidEntryPoint
class WebSocketService : Service() {
    private var server: NettyApplicationEngine? = null
    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var logsDao: LogsDao

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        serviceScope.launch {
            startServer()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
        server?.stop(0, 0)
    }

    private fun startServer() {
        Log.d("SERVICE", "STARTED")
        server = embeddedServer(Netty, 8080) {
            install(WebSockets)
            routing {
                webSocket("/echo") {
                    send(Frame.Text("connection established"))
                    send(Frame.Text("up"))
                    incoming.consumeEach { frame ->
                        if (frame is Frame.Text) {
                            val receivedText = frame.readText()
                            send(Frame.Text("Gesture performed: $receivedText"))
                            logsDao.insertLog(LogEntity(message = receivedText))
                        }
                    }
                }
            }
        }.start(wait = true)
    }
}
