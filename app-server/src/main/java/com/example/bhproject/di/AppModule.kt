package com.example.bhproject.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.bhproject.data.db.LogsDatabase
import com.example.bhproject.data.db.dao.LogsDao
import com.example.bhproject.data.repository.ServerRepositoryImpl
import com.example.bhproject.domain.repository.ServerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideServerRepository(logsDao: LogsDao): ServerRepository {
        return ServerRepositoryImpl(logsDao)
    }

    @Provides
    fun provideLogsDatabase(context: Context): LogsDatabase {
        return Room.databaseBuilder(
            context,
            LogsDatabase::class.java,
            "logs"
        ).allowMainThreadQueries().build()
    }

    @Provides
    fun provideLogsDao(logsDatabase: LogsDatabase): LogsDao {
        return logsDatabase.LogsDao()
    }

    @Provides
    @Singleton
    fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}