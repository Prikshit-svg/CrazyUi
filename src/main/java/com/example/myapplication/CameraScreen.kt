package com.example.myapplication

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlin.contracts.contract

@Composable
fun CameraScreen(){
    val context= LocalContext.current
    val lifecycleOwner= LocalLifecycleOwner.current
    var hasCameraPermission= remember{ //self checks whether the user has already granted camera permission or not
        ContextCompat.checkSelfPermission(context,
            Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED
    }
    val permissionsLauncher= rememberLauncherForActivityResult(//creates a permission launcher
        // which can be called later to request permission
        ActivityResultContracts.RequestPermission()){
        granted->//assigns the value to the variable based on whether user clicked true or false
        hasCameraPermission=granted
    }
    val imageCapture=rememberImageCaptureUseCase()
    if(hasCameraPermission){
        CameraPreview(
            imageCapture,
            lifecycleOwner,
            modifier= Modifier.fillMaxSize()
        )
    }
    Scaffold(
        bottomBar = {
            if (hasCameraPermission){
                BottomAppBar {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick={ 
                             // Trigger capture when button is pressed
                                capturePhoto(
                                    imageCapture = imageCapture,
                                    context = context
                                )
                             }
                        ) {Text("Capture") }
                    }
                }
            }
        }
    ) { it ->
        Box(Modifier.fillMaxSize()
            .padding(it)){

            if(hasCameraPermission){
                CameraPreview(imageCapture,lifecycleOwner,Modifier.fillMaxSize())
            }
            else{//ask for permission
                Column(Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center

                    ) {
                    Text("this app needs your Camera Permission")
                }
                Spacer(Modifier.padding(16.dp))
                Button(onClick = {
                    permissionsLauncher.launch(Manifest.permission.CAMERA)


        }) {
            Text("Grant Permission")
        }
            }

    }
}}

@Preview(showSystemUi = true)
@Composable
fun CameraScreenPreview(){
    CameraScreen()
}