package com.example.app_client.di

import com.example.app_client.data.network.ApiService
import com.example.app_client.data.repository.ClientRepositoryImpl
import com.example.app_client.domain.repository.ClientRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ClientModule {
    @Provides
    fun provideClientRepository(
        apiService: ApiService
    ): ClientRepository {
        return ClientRepositoryImpl(apiService)
    }

}