package com.example.bhproject.domain.repository

import com.example.bhproject.domain.model.Log
import kotlinx.coroutines.flow.Flow

interface ServerRepository {
    suspend fun getLogs(): List<Log>
}