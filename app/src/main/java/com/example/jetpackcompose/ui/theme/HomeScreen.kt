package com.example.jetpackcompose.ui.theme

import android.widget.Space
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.*
import com.example.jetpackcompose.R
import com.google.android.material.chip.ChipGroup

@ExperimentalFoundationApi
@Composable
fun HomeScreen() {
    Box(modifier = Modifier
        .background(DeepBlue)
        .fillMaxSize()) {
        Column {
            Greetings(name = "Revathi")
            ChipSection(chips = listOf("Sweet Sleep", "Insomnia","Depression"))
            CurrentMeditation()
            FeatureCreation(features = listOf(
                Feature(title = "Sleep Meditation", R.drawable.ic_baseline_headset_24, BlueViolet1, BlueViolet2, BlueViolet3),
                Feature(title = "Tips for Sleeping", R.drawable.ic_baseline_videocam_24, LightGreen1, LightGreen2, LightGreen3),
                Feature(title = "Night Island", R.drawable.ic_baseline_headset_24, OrangeYellow1, OrangeYellow2, OrangeYellow3),
                Feature(title = "Calming Sounds", R.drawable.ic_baseline_headset_24, Beige1, Beige2, Beige3)
            ))
        }
        CustomBottomMenu(items = listOf(
            BottomMenuContent("Home", R.drawable.ic_baseline_home_24),
            BottomMenuContent("Meditate", R.drawable.ic_baseline_insert_emoticon_24),
            BottomMenuContent("Sleep", R.drawable.ic_baseline_bedtime_24),
            BottomMenuContent("Music", R.drawable.ic_baseline_music_note_24),
            BottomMenuContent("Profile", R.drawable.ic_baseline_person_24)
        ), modifier = Modifier.align(Alignment.BottomCenter))
    }
    
}

@Composable
fun Greetings(name:String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)) {
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = "Hello!! Good Morning $name",
                style = MaterialTheme.typography.h6,
                color = TextWhite)
            /*modifier = Modifier.padding(horizontal = 10.dp, vertical = 15.dp),
            style = TextStyle(fontSize = 24.sp, color = Color.Black, fontWeight = FontWeight.Bold)*/
            Text(text = "We wish you a Good day!!",
                style = MaterialTheme.typography.body1,
                color = TextWhite)
        }
        Icon(painter = painterResource(id = R.drawable.ic_baseline_search_24),
            contentDescription = "Search",
            tint = Color.White,
            modifier = Modifier.size(40.dp))
    }
}

@Composable
fun ChipSection(chips : List<String>) {
    var selectedChipIndex by remember {
        mutableStateOf(0)
    }

    LazyRow() {
        items(chips.size){
            Box(contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .clickable {
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if (selectedChipIndex == it) ButtonBlue else DarkerButtonBlue
                    )
                    .padding(15.dp)
            ) {
                Text(text = chips[it], color = TextWhite)
            }
        }
    }
}

@Composable
fun CurrentMeditation(color: Color = LightRed){
Row(verticalAlignment = Alignment.CenterVertically,
horizontalArrangement = Arrangement.SpaceBetween,
modifier = Modifier
    .padding(10.dp)
    .clip(RoundedCornerShape(10.dp))
    .background(color)
    .padding(horizontal = 15.dp, vertical = 20.dp)
    .fillMaxWidth()
) {
    Column {
        Text(text = "Daily Thought", style = MaterialTheme.typography.h6,
            color = TextWhite)
        Text(text = "Meditation : 3-10mins",
            style = MaterialTheme.typography.body2,
            color = TextWhite)
    }

    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(ButtonBlue)
            .padding(10.dp)) {
        Icon(painter = painterResource(id = R.drawable.ic_baseline_play_circle_24),
            contentDescription = "Play it",
        tint = Color.White,
        modifier = Modifier.size(36.dp))

    }
}
}

@ExperimentalFoundationApi
@Composable
fun FeatureCreation(features : List<Feature>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Features",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(16.dp))
        LazyVerticalGrid(cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(start = 7.5.dp, end = 7.5.dp, bottom = 100.dp),
            modifier = Modifier.fillMaxHeight()) {
            items(features.size) {
                FeatureItem(feature = features[it])
            }
        }
    }
}

@Composable
fun FeatureItem(feature : Feature) {
    BoxWithConstraints(modifier = Modifier
        .padding(7.5.dp)
        .aspectRatio(1f)
        .clip(RoundedCornerShape(10.dp))
        .background(feature.DarkColor)) {
        val width = constraints.maxWidth
        val height = constraints.maxHeight

        //medium colored path
        val mediumColoredPoint1 = Offset(0f, height*0.3f)
        val mediumColoredPoint2 = Offset(width*0.1f, height*0.35f)
        val mediumColoredPoint3 = Offset(width*0.4f, height*0.05f)
        val mediumColoredPoint4 = Offset(width*0.75f, height*0.7f)
        val mediumColoredPoint5 = Offset(width*1.4f, -height.toFloat())

        val mediumColoredPath = Path().apply {
            moveTo(mediumColoredPoint1.x,mediumColoredPoint1.y)
            standardQuardFromTo(mediumColoredPoint1, mediumColoredPoint2)
            standardQuardFromTo(mediumColoredPoint2, mediumColoredPoint3)
            standardQuardFromTo(mediumColoredPoint3, mediumColoredPoint4)
            standardQuardFromTo(mediumColoredPoint4, mediumColoredPoint5)

            lineTo(width.toFloat()+100f, height.toFloat()+100f)
            lineTo(-100f, height.toFloat()+100f)
            close()
        }

        //light colored path
        val lightColoredPoint1 = Offset(0f, height*0.4f)
        val lightColoredPoint2 = Offset(width*0.1f, height*0.4f)
        val lightColoredPoint3 = Offset(width*0.3f, height*0.35f)
        val lightColoredPoint4 = Offset(width*0.65f, height.toFloat())
        val lightColoredPoint5 = Offset(width*1.4f, -height.toFloat()/3f)

        val lightColoredPath = Path().apply {
            moveTo(lightColoredPoint1.x,lightColoredPoint1.y)
            standardQuardFromTo(lightColoredPoint1, lightColoredPoint2)
            standardQuardFromTo(lightColoredPoint2, lightColoredPoint3)
            standardQuardFromTo(lightColoredPoint3, lightColoredPoint4)
            standardQuardFromTo(lightColoredPoint4, lightColoredPoint5)

            lineTo(width.toFloat()+100f, height.toFloat()+100f)
            lineTo(-100f, height.toFloat()+100f)
            close()
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawPath(path = mediumColoredPath, color = feature.mediumColor)
            drawPath(path = lightColoredPath, color = feature.lightColor)
        }
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)) {
            Text(text = feature.title,
                style = MaterialTheme.typography.h6,
                lineHeight = 26.sp,
                modifier = Modifier.align(Alignment.TopStart))
            Icon(painter = painterResource(id = feature.iconId), contentDescription = feature.title,
                tint = Color.White, modifier = Modifier.align(Alignment.BottomStart))
            Text(text = "Start", color = TextWhite, fontWeight = FontWeight.Bold,
                fontSize = 14.sp, modifier = Modifier
                    .clickable {
                        //handle the click
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .align(Alignment.BottomEnd)
                    .background(ButtonBlue)
                    .padding(vertical = 6.dp, horizontal = 15.dp))
        }
    }

}

@Composable
fun CustomBottomMenu(items : List<BottomMenuContent>,
modifier: Modifier = Modifier,
activeHighlightColor : Color = ButtonBlue,
activeTextColor : Color = Color.White,
inActiveTextColor : Color = AquaBlue,
initialSelectedIndex : Int = 0) {

    var selectedItemsIndex by remember {
        mutableStateOf(initialSelectedIndex)
    }

    Row(horizontalArrangement = Arrangement.SpaceAround,
    verticalAlignment = Alignment.CenterVertically,
    modifier = modifier
        .fillMaxWidth()
        .background(DeepBlue)
        .padding(15.dp)) {
        items.forEachIndexed { index, item ->

            BottomMenuItem(item = item,
            isSelected = index == selectedItemsIndex,
            activeHighlightColor = activeHighlightColor,
            activeTextColor = activeTextColor,
            inActiveTextColor = inActiveTextColor) {
                selectedItemsIndex = index
            }
        }
    }
}

@Composable
fun BottomMenuItem(
    item: BottomMenuContent,
    isSelected: Boolean = false,
    activeHighlightColor: Color = DeepBlue,
    activeTextColor: Color = Color.White,
    inActiveTextColor: Color = AquaBlue,
    onItemClick: () -> Unit,
) {
Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center,
    modifier = Modifier.clickable {
        onItemClick()
    }
) {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(10.dp)
            .background(if (isSelected) activeHighlightColor else Color.Transparent)) {
        Icon(painter = painterResource(id = item.iconId), contentDescription = item.title,
        tint = if (isSelected) activeTextColor else inActiveTextColor,
        modifier = Modifier.size(20.dp))
    }
    Text(text = item.title, color = if (isSelected) activeTextColor else inActiveTextColor)

}
}