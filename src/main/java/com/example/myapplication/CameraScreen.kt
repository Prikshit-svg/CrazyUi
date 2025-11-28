package com.example.myapplication

import android.Manifest
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.myapplication.viewmodels.CameraViewmodel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CameraScreen(viewmodel: CameraViewmodel = viewModel() ){
    val context= LocalContext.current
    val lifecycleOwner= LocalLifecycleOwner.current
    LaunchedEffect(Unit) {
        viewmodel.initializeCamera(context)
    }
    val permissionsLauncher= rememberLauncherForActivityResult(//creates a permission launcher
        // which can be called later to request permission
        ActivityResultContracts.RequestPermission()){
        granted-> //assigns the value to the variable based on whether user clicked true or false
        viewmodel.onPermissionResult(granted)
    }
    val imageCaptureUseCase= rememberImageCaptureUseCase()
    LaunchedEffect(imageCaptureUseCase) {
        viewmodel.setImageCaptureInstance(imageCaptureUseCase)
    }

    if(viewmodel.hasCameraPermission){
        CameraPreview(
            viewmodel.imageCapture,
            lifecycleOwner,
            modifier = Modifier.fillMaxSize()
        )
    }
    Scaffold(
        bottomBar = {
            if (viewmodel.hasCameraPermission){
                BottomAppBar {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick={
                                viewmodel.onPhotoCaptureClicked(context)
                             }
                        ) {Text("Capture") }
                    }
                }
            }
        }
    ) { it ->
        Box(Modifier.fillMaxSize()
            .padding(it)){

            if(viewmodel.hasCameraPermission){
                CameraPreview(viewmodel.imageCapture,lifecycleOwner,Modifier.fillMaxSize())
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
   // CameraScreen()
}