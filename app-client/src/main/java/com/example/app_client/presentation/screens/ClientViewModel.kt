package com.example.app_client.presentation.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app_client.data.repository.ClientRepositoryImpl
import com.example.app_client.domain.model.Log
import com.example.app_client.services.ClientAccessibilityService
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val repositoryImpl: ClientRepositoryImpl
) : ViewModel() {
    fun startClient(ip: String, port: Int) {
        viewModelScope.launch {
            repositoryImpl.connect(ip, port)
        }
    }

    private val accessibilityService: ClientAccessibilityService by lazy {
        ClientAccessibilityService()
    }

    fun stopClient() {
        viewModelScope.launch {
            repositoryImpl.disconnect()
        }
    }

    fun sendMessage(message: Log) {
        viewModelScope.launch {
            repositoryImpl.sendMessage(message)
        }
    }

    fun openChrome(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            android.util.Log.e("ERROR!", "Browser Chrome not found!")
        }
    }

    fun moveUp() {
        accessibilityService.performSwipe("up")
    }


}