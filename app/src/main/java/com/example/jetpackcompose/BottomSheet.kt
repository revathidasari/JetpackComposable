package com.example.jetpackcompose

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun BottomSheet() {
    BottomSheetWithDraggabilityAndClickable()
}

@ExperimentalMaterialApi
@Composable
fun BottomSheetWithDraggabilityAndClickable() {
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed
        , animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy))
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val scope = rememberCoroutineScope()
    BottomSheetScaffold(scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
            contentAlignment = Alignment.Center) {
                Text(text = "Bottom Sheet",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold)
        }
    },
    sheetBackgroundColor = Color.Yellow,
        sheetPeekHeight = 0.dp//height of bottom sheet can be modified here
    ) {
        Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                Button(onClick = {
                    scope.launch {
                        if (sheetState.isCollapsed) {
                            sheetState.expand()
                        } else {
                            sheetState.collapse()
                        }
                    }
                }
                ) {
                Text(text = "Toggle Sheet")
                }
                Text(text = "progressing value ${sheetState.progress.fraction}")
            }
        }
    }
}
