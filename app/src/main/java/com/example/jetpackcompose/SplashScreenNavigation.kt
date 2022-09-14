package com.example.jetpackcompose

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

@Composable
fun SplashScreenNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreenPage(navController = navController)
        }
        composable("sample_screen") {
            SamplePage()
        }
    }
}

@Composable
fun SplashScreenPage(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(targetValue = 1f,
            animationSpec = tween(durationMillis = 500,
            easing = {
                OvershootInterpolator(2f).getInterpolation(it)
            }
            )
        )
        delay(3000L)
        navController.navigate("sample_screen")
    }


    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.ic_baseline_grass_24),
            contentDescription = "Grass Logo",
        modifier = Modifier.size(150.dp).scale(scale.value))
    }
}

@Composable
fun SamplePage() {
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
        Text(text = "Screen After Splashing Logo", textAlign = TextAlign.Center)
    }
}