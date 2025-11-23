package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.SocialLink

@Composable
fun SocialLinkRow(link: SocialLink, onItemClick: (String) -> Unit) {
    val context= LocalContext.current
    Row (modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 8.dp, vertical = 16.dp),
        // This ensures the button and text are aligned vertically
        verticalAlignment = Alignment.CenterVertically,
        // This pushes the Text to the end, leaving space for the button
        horizontalArrangement = Arrangement.SpaceBetween
        ){

        SocialButton(


            icon =link.iconRes,
            onClick = { onItemClick(link.url) }
        )
        Text(
            text =link.label,
            modifier = Modifier.padding(end = 8.dp) ,
            style = MaterialTheme.typography.bodyMedium


        )
    }
}