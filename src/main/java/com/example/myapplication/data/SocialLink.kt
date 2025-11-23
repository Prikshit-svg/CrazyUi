package com.example.myapplication.data

import com.example.myapplication.R
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class SocialLink(@DrawableRes val iconRes: Int,
                      val label: String,
                      val url: String,

                      )

fun getSocialLinks(): List<SocialLink> =
    listOf(SocialLink(
        iconRes=R.drawable.igimage,
        label="Instagram",
        url="https://www.instagram.com/prikshit3001/",

    ),
        SocialLink(
            iconRes= R.drawable.img,
            label="Snapchat",
            url="https://www.snapchat.com/add/shinigami2607?share_id=4UgedkCOGos&locale=en-IN",

        ),
        SocialLink(
            iconRes=R.drawable.img_2,
            label="LinkedIn",
            url="https://www.linkedin.com/in/prikshit-rana-420533330/",

        ),
        SocialLink(
            iconRes=R.drawable.img_1,
            label="X",
            url="https://x.com/prikshit134901",

        ),


    )
