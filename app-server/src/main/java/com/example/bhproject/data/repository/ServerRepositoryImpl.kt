package com.example.bhproject.data.repository

import com.example.bhproject.data.db.dao.LogsDao
import com.example.bhproject.data.mapper.toLogsItem
import com.example.bhproject.domain.model.Log
import com.example.bhproject.domain.repository.ServerRepository
import io.ktor.server.netty.NettyApplicationEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ServerRepositoryImpl @Inject constructor(
    private val logsDao: LogsDao
) : ServerRepository {
    private var server: NettyApplicationEngine? = null

    override suspend fun getLogs(): List<Log> {
        return withContext(Dispatchers.IO) {
            logsDao.getAllLogs().toLogsItem()
        }
    }

}