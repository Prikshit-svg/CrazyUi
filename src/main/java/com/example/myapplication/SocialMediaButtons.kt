package com.example.myapplication



import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.unit.dp



@Composable
fun SocialButton(


    icon : Int,
    onClick : () -> Unit = {}
){
    var pressedState by remember { mutableStateOf(false)  }

   /* val bgColor by animateColorAsState(
        targetValue = if(pressedState) pressedColor else Color.Transparent,
        animationSpec = tween(200),
        label="bg"
    )

    */
    val scale by animateFloatAsState(
        targetValue = if(pressedState) 0.9f else 1f,
        animationSpec = tween(200),
        label="scale"

    )
    val offsetY by animateDpAsState(
        targetValue = if (pressedState) 0.dp else (-12).dp,
        animationSpec = tween(300),
        label = "offsetY"
    )
    Box(Modifier.size(52.dp)
        .graphicsLayer{
            scaleX=scale
            scaleY=scale
        }

        

        .pointerInput(Unit){
            detectTapGestures (
                onTap = { onClick() },
                onPress = {
                    pressedState=true
                    tryAwaitRelease()
                    pressedState=false
                }
            )


        }.clip(RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ) {

        Image(
            painter = painterResource(icon),
            contentDescription = null, // Provide a description for accessibility
            modifier = Modifier
                .size(48.dp) // Give the logo a specific size
                .offset(y = offsetY)
        )
    }
}

