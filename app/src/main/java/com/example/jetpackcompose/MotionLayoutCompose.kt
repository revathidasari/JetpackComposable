package com.example.jetpackcompose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene

@ExperimentalMotionApi
@Composable
fun MotionLayoutCompose() {
    Column {
        var progress by remember{
            mutableStateOf(0f)
        }
            ProfileHeaders(progress = progress)
            Spacer(modifier = Modifier.height(12.dp))

            Slider(value = progress, onValueChange = {
                progress = it
            },
            modifier = Modifier.padding(32.dp))
    }
}

@ExperimentalMotionApi
@Composable
fun ProfileHeaders(progress:Float) {
    val currentContext = LocalContext.current
    val motionScene = remember {
        currentContext
            .resources
            .openRawResource(R.raw.motion)
            .readBytes()
            .decodeToString()
    }
    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {

        val propertiesPic = motionProperties(id = "profile_pic")
        val propertiesUser = motionProperties(id = "username")
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .layoutId("box"))
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_local_florist_24),
            contentDescription = "null",
            modifier = Modifier
                .layoutId("profile_pic")
                .border(width = 2.dp,
                    color = propertiesPic.value.color("background"),//Color.Green,
                    shape = CircleShape
                )
                .clip(CircleShape))
        Text(text = "Motion Composing",
            color = propertiesUser.value.color("background"),//Color.White,
            fontSize = 24.sp,
            modifier = Modifier.layoutId("username"))
    }
}


