package com.example.common

object NetworkConfig {
    var serverPort = 8000

    fun saveConfig(port: Int) {
        serverPort = port
    }

    fun getConfig(): Int {
        return serverPort
    }
}