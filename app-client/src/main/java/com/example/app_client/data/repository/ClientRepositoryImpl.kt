package com.example.app_client.data.repository

import com.example.app_client.data.network.ApiService
import com.example.app_client.domain.model.Log
import com.example.app_client.domain.repository.ClientRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClientRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ClientRepository {
    override suspend fun connect(ip: String, port: Int) {
        apiService.connect(ip, port)
    }

    override suspend fun disconnect() {
        apiService.disconnect()
    }

    override suspend fun sendMessage(message: Log) {
        apiService.sendMessage(message)
    }



}