package com.example.myapplication


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.data.getSocialLinks

@Composable
fun Drawer(){
    val context= LocalContext.current
    val socialLinks= getSocialLinks()
   LazyColumn (modifier= Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){
       item {
           Text(
               text = "My Social Media Handles",
               modifier = Modifier.padding(bottom = 16.dp),
               style = MaterialTheme.typography.titleLarge,
               fontWeight = FontWeight.Bold
           )
       }
       items(socialLinks){
           link->SocialLinkRow(link = link, onItemClick = { url->
           val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
           context.startActivity(intent)
       })
       }
    }
}

@Preview(showSystemUi = true)
@Composable
fun DrawerPreview(){
    Drawer()
}