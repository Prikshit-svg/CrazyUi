package com.example.myapplication.data

import com.example.myapplication.R
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class SocialLink(@DrawableRes val iconRes: Int,
                      val label: String,
                      val url: String,
    val color : Color
                      )

fun getSocialLinks(): List<SocialLink> =
    listOf(SocialLink(
        iconRes=R.drawable.igimage,
        label="Instagram",
        url="https://www.instagram.com/prikshit3001/",
        color = Color(0xFFE4405F) // Instagram Pink
    ),
        SocialLink(
            iconRes= R.drawable.img,
            label="Snapchat",
            url="https://www.snapchat.com/add/shinigami2607?share_id=4UgedkCOGos&locale=en-IN",
            color = Color(0xFFFFFC00) // Snapchat Yellow
        ),
        SocialLink(
            iconRes=R.drawable.img_2,
            label="LinkedIn",
            url="https://www.linkedin.com/in/prikshit-rana-420533330/",
            color = Color(0xFF0077B5) // LinkedIn Blue
        ),
        SocialLink(
            iconRes=R.drawable.img_1,
            label="X",
            url="https://x.com/prikshit134901",
            color = Color(0xFF000000) // X Black
        ),


    )
