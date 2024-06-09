package com.example.app_client.domain.repository

import com.example.app_client.domain.model.Log
import kotlinx.coroutines.flow.Flow

interface ClientRepository {
    suspend fun connect(ip: String, port: Int)
    suspend fun disconnect()
    suspend fun sendMessage(message: Log)
}