package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Environment
import android.provider.CloudMediaProviderContract.AlbumColumns.DISPLAY_NAME
import android.provider.MediaStore
import android.widget.Toast
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.takePicture
import androidx.camera.video.FileOutputOptions
import androidx.core.content.ContextCompat
import androidx.camera.core.ImageCapture.OutputFileResults


fun capturePhoto(
    imageCapture : ImageCapture,
    context : Context
){
    val name= SimpleDateFormat("yyyyMMdd_HHmmss", java.util.Locale.US)
        .format(System.currentTimeMillis())
//This part prepares the metadata that Android uses to store your photo in the system
// gallery via MediaStore

    val contentValues= ContentValues().apply(block = {
        put(MediaStore.MediaColumns.DISPLAY_NAME,name)//assigns the image a name
        put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg")//assigns the image a type
        put(MediaStore.Images.Media.RELATIVE_PATH,
            Environment.DIRECTORY_PICTURES +"/CameraX-Image")//assigns the image a location or a directory path where the image will be saved


    })

    val outputOption= ImageCapture.OutputFileOptions.Builder(/* It tells CameraX:
    Where to save the image.What metadata to use (name, folder, type).That the target is MediaStore storage.CameraX directly uses this to insert the image into Android’s media database.*/

        context.contentResolver,
        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
        contentValues

    ).build()/*
    “This file should be stored in external shared storage as an image.”
Even though it's called external, this usually refers to:
/storage/emulated/0/ (internal shared storage)
Not necessarily an SD card.
Other examples:
MediaStore.Video.Media.EXTERNAL_CONTENT_URI → for videos
MediaStore.Audio.Media.EXTERNAL_CONTENT_URI → for music
    */

    imageCapture.takePicture(
        outputOption,
        ContextCompat.getMainExecutor(context),
        object : ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults :ImageCapture.OutputFileResults) {
                Toast.makeText(context,
                    "Photo captured successfully",
                    Toast.LENGTH_LONG).show()
            }

            override fun onError(exception : ImageCaptureException){
                Toast.makeText(context,
                    "Failed to save the image ${exception.message}",
                    Toast.LENGTH_LONG).show()
        }



})
}