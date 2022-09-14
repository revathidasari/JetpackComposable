package com.example.jetpackcompose

import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat

@Composable
fun ParallaxScrollEffectByMultipleLayer() {
 /*   Box(modifier = Modifier
        .fillMaxSize()
        .padding(12.dp))  {*/
        val moonScrollSpeed = 0.08f
        val midBgScrollSpeed = 0.03f

        var moonOffset by remember {
            mutableStateOf(0f)
        }
        var midBgOffset by remember {
            mutableStateOf(0f)
        }

        val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp
        val lazyListState = rememberLazyListState()

        val nestedScrollConnection = object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val layoutInfo = lazyListState.layoutInfo
                //check if the first item is visible
                if (lazyListState.firstVisibleItemIndex == 0){
                    return Offset.Zero
                }
                if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount-1) {
                    return Offset.Zero
                }
                moonOffset += delta * moonScrollSpeed
                midBgOffset += delta * midBgScrollSpeed
                return Offset.Zero//Offset(0f, delta/2)
            }
        }

        LazyColumn(modifier = Modifier.fillMaxSize().nestedScroll(nestedScrollConnection),
        state = lazyListState) {
            items(10) {
                Text(text = "Sample item ",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp))
            }
            item {
                //cliptobounds will override the one on one
                Box(modifier = Modifier.clipToBounds().fillMaxWidth()
                    .height(imageHeight + midBgOffset.toDp())
                    .background(Brush.verticalGradient(listOf(
                        Color(0xFFE66428),
                        Color(0xFFFF9800)
                    )))) {
                    Image(painter = painterResource(id = R.drawable.ic_moonbg),
                        contentDescription = "Moon Bg",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.BottomCenter,
                    modifier = Modifier.matchParentSize()
                        .graphicsLayer {
                            translationY = moonOffset
                        })

                    Image(painter = painterResource(id = R.drawable.ic_midbg),
                        contentDescription = "Mid Bg",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.BottomCenter,
                        modifier = Modifier.matchParentSize()
                            .graphicsLayer {
                                translationY = midBgOffset
                            }
                    )

                    Image(painter = painterResource(id = R.drawable.ic_outerbg),
                        contentDescription = "Outer Bg",
                        contentScale = ContentScale.FillWidth,
                        alignment = Alignment.BottomCenter,
                        modifier = Modifier.matchParentSize())
                }
            }
            items(15) {
                Text(text = "Sample item $it",
                modifier = Modifier.fillMaxWidth().padding(20.dp))
            }
        }
    }

private fun Float.toDp(): Dp {
    return (this / Resources.getSystem().displayMetrics.density).dp
}
//}