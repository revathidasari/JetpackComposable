package com.example.jetpackcompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@ExperimentalFoundationApi
@Composable
fun CustomLazyGrid() {
    val lazyState = rememberLazyListState(
        initialFirstVisibleItemIndex = 10
    )
/*val scope = rememberCoroutineScope()

    scope.launch {
        lazyState.animateScrollToItem(26)
    }*/
    LazyVerticalGrid(
        state = lazyState,
        cells =
        //GridCells.Fixed(5),
        GridCells.Adaptive(100.dp),
        content = {
            items(140) {i->
                Box(modifier = Modifier
                    .padding(10.dp)
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.Cyan),
                contentAlignment = Alignment.Center) {
                    Text(text = "item $i")
                }
            }
        })
}