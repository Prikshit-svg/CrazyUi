package com.example.myapplication

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.camera.core.CameraProvider
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.camera.core.Preview
import androidx.core.content.ContextCompat

@Composable
fun CameraPreview(
    imageCapture: ImageCapture,
    lifecycleOwner: LifecycleOwner,
    modifier : Modifier=Modifier//This composable accepts a Modifier, but if you don’t
// provide one, it will use a default empty modifier.
){
    val context= LocalContext.current

    val cameraProviderFuture= remember{
        ProcessCameraProvider.getInstance(context)
    }/*This is the main entry point for CameraX. It gives you control over:
    Connecting to the camera
    Binding / unbinding use cases
     */
    AndroidView(
        factory = {
            ctx ->    //Compose cannot directly show camera output because: CameraX uses traditional Android Views Compose is not a View-based system Inside factory, we create the actual camera preview view.
       val previewView= PreviewView(ctx)
            val preview=Preview.Builder().build()
            val cameraSelector= CameraSelector.DEFAULT_BACK_CAMERA
            cameraProviderFuture.addListener({
                try{
                    val cameraProvider= cameraProviderFuture.get()
                    cameraProvider.unbindAll()
                    preview.setSurfaceProvider(previewView.surfaceProvider)//Send your live camera frames to this PreviewView
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageCapture
                    )
                }
                catch (e:Exception){
                    Log.e(TAG, "Camera binding failed", e)

                }
            }, ContextCompat.getMainExecutor(ctx))
            previewView
        },modifier=modifier
    )

}