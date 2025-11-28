package com.example.myapplication.viewmodels

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.camera.core.ImageCapture
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.capturePhoto
import kotlinx.coroutines.launch

class CameraViewmodel : ViewModel() {

    // Correctly expose hasCameraPermission as an observable State
    var hasCameraPermission: Boolean by mutableStateOf(false)
        private set // This means it can only be set from within the ViewModel

    // Correctly expose imageCapture as an observable State
    var imageCapture: ImageCapture? by mutableStateOf(null)
        private set // This means it can only be set from within the ViewModel

    fun initializeCamera(context: Context) {
        // Check permission and update the observable state
        hasCameraPermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun onPermissionResult(granted: Boolean) {
        // Update the observable state when permission result is received
        hasCameraPermission = granted
    }

    fun onPhotoCaptureClicked(context: Context) {
        // Use the public 'imageCapture' property which is now observable
        imageCapture?.let { capture ->
            viewModelScope.launch {
                // Trigger capture when button is pressed
                capturePhoto(
                    imageCapture = capture, // Use the non-nullable 'capture'
                    context = context
                )
            }
        }
    }

    fun setImageCaptureInstance(instance: ImageCapture) {
        // Update the observable imageCapture state
        this.imageCapture = instance
    }
}