package com.example.bhproject.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.bhproject.data.db.LogEntity

@Dao
interface LogsDao {
    @Insert
    fun insertLog(log: LogEntity)

    @Query("SELECT * FROM logs ORDER BY id DESC")
    fun getAllLogs(): List<LogEntity>
}