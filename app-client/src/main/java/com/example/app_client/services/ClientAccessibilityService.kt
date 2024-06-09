package com.example.app_client.services

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.GestureDescription
import android.graphics.Path
import android.view.accessibility.AccessibilityEvent

class ClientAccessibilityService : AccessibilityService() {
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        TODO("Not yet implemented")
    }

    override fun onInterrupt() {
        TODO("Not yet implemented")
    }

    fun performSwipe(direction: String) {
        val path = Path()
        when (direction) {
            "up" -> {
                path.moveTo(500f, 1000f)
                path.lineTo(500f, 500f)
            }
            "down" -> {
                path.moveTo(500f, 500f)
                path.lineTo(500f, 1000f)
            }
        }

        val gestureBuilder = GestureDescription.Builder()
        gestureBuilder.addStroke(GestureDescription.StrokeDescription(path, 0, 500))
        dispatchGesture(gestureBuilder.build(), null, null)
    }
}