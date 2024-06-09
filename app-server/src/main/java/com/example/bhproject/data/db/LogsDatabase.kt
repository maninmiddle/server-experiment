package com.example.bhproject.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bhproject.data.db.dao.LogsDao

@Database(entities = [LogEntity::class], version = 1)
abstract class LogsDatabase : RoomDatabase() {
    abstract fun LogsDao(): LogsDao
}