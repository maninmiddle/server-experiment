package com.example.bhproject.presentation.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bhproject.data.repository.ServerRepositoryImpl
import com.example.bhproject.domain.model.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServerScreenViewModel @Inject constructor(
    private val repositoryImpl: ServerRepositoryImpl
) : ViewModel() {
    private val _logs = MutableStateFlow(listOf<Log>())
    val logs: StateFlow<List<Log>>
        get() = _logs

    fun getLogs() {
        viewModelScope.launch {
            val logsList = repositoryImpl.getLogs()
            _logs.value = logsList
            android.util.Log.d("LOGS", logsList.toString())
        }
    }
}