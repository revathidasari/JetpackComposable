package com.example.jetpackcompose

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class ImageWithText(
    val text: String,
    val iconId: Painter
)
