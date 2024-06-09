package com.example.bhproject.data.mapper

import com.example.bhproject.data.db.LogEntity
import com.example.bhproject.domain.model.Log
import kotlin.math.log

fun List<LogEntity>.toLogsItem(): List<Log> {
    return this.map { logEntity ->
        Log(
            id = logEntity.id,
            message = logEntity.message
        )
    }
}