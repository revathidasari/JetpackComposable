package com.example.jetpackcompose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.selects.select

@ExperimentalFoundationApi
@Composable
fun ProfileScreen() {
    var selectedTabIndex by remember {
        mutableStateOf(0)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp)) {
        TopBar(modifier = Modifier.padding(6.dp), titleText = "revathi_dasari_14_10")
        Spacer(modifier = Modifier.height(5.dp))
        ProfileSection()
        Spacer(modifier = Modifier.height(8.dp))
        ButtonSection(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.height(15.dp))
        HighlightSection(highlights = listOf(
            ImageWithText(text = "Youtube", iconId = painterResource(id = R.drawable.ic_baseline_ondemand_video_24)),
            ImageWithText(text = "Q&A", iconId = painterResource(id = R.drawable.ic_baseline_question_answer_24)),
            ImageWithText(text = "Discard", iconId = painterResource(id = R.drawable.ic_baseline_redeem_24)),
            ImageWithText(text = "Telegram", iconId = painterResource(id = R.drawable.ic_baseline_send_24))
        ), modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp))
        Spacer(modifier = Modifier.height(8.dp))
        PostTabView(imageWithText = listOf(
            ImageWithText(text = "Posts", iconId = painterResource(id = R.drawable.ic_baseline_grid_on_24)),
            ImageWithText(text = "Reels", iconId = painterResource(id = R.drawable.ic_baseline_videocam_24)),
            ImageWithText(text = "IGTV", iconId = painterResource(id = R.drawable.ic_baseline_movie_24)),
            ImageWithText(text = "Profile", iconId = painterResource(id = R.drawable.ic_baseline_account_box_24))
        )) {
            selectedTabIndex = it
        }
        when(selectedTabIndex) {
            0 -> PostsSection(posts = listOf(
                painterResource(id = R.drawable.composing),
                painterResource(id = R.drawable.music_knob),
                painterResource(id = R.drawable.composing),
                painterResource(id = R.drawable.music_knob),
                painterResource(id = R.drawable.composing),
                painterResource(id = R.drawable.music_knob)
            ), modifier = Modifier.fillMaxWidth())
        }
    }
}

@Composable
fun TopBar(
    titleText: String,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier.fillMaxWidth()) {
        Icon(imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back Move",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp))
        Text(text = titleText,
            overflow = TextOverflow.Ellipsis,
            color = Color.Black,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold)
        Icon(painter = painterResource(id = R.drawable.ic_baseline_notifications_24),
            contentDescription = "Notifications",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp))
        Icon(painter = painterResource(id = R.drawable.ic_baseline_more_vert_24),
            contentDescription = "More Info",
            tint = Color.Black,
            modifier = Modifier
                .size(24.dp))
    }
}

@Composable
fun ProfileSection(modifier: Modifier = Modifier) {
    Column(
    modifier = modifier.padding(8.dp)) {
        Row(
        horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()) {
            RoundedImage(modifier = Modifier
                .size(100.dp)
                .weight(3f),
                imageSource = painterResource(id = R.drawable.music_knob),
                "Sample Image")
            Spacer(modifier = Modifier.width(5.dp))
            StatusSection(modifier = Modifier.weight(7f))
        }
        Spacer(modifier = Modifier.height(5.dp))
        ProfileDescription(
            subject = "Jetpack Sample View",
            briefDescription = "A brief description about UI which is \n" +
                    "similar to Instagram Profile by using Jetpack",
            url = "https://abcdef.com/c/revathidasari",
            followedPeople = listOf("abcd", "efgh"),
            followedCount = 10)


    }
}

@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    imageSource: Painter,
    text: String? = null,
) {
    Image(painter = imageSource,
        contentDescription = text,
        modifier = modifier
            .aspectRatio(ratio = 1f, matchHeightConstraintsFirst = true)
            .border(
                width = 1.dp,
                color = Color.LightGray,
                shape = CircleShape
            )
            .padding(3.dp)
            .clip(CircleShape))
}

@Composable
fun StatusSection(modifier: Modifier = Modifier) {
    Row(verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier) {
        ProfileStatus(
            statusText = "123",
            statusType = "Posts")
        ProfileStatus(
            statusText = "0K",
            statusType = "Followers")
        ProfileStatus(
            statusText = "0",
            statusType = "Following")
    }
}

@Composable
fun ProfileStatus(
    modifier: Modifier = Modifier,
    statusText: String,
    statusType: String,
) {
    Column(
    verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier) {
        Text(text = statusText,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = statusType,
        fontSize = 18.sp)
    }
}

@Composable
fun ProfileDescription(modifier: Modifier = Modifier,
subject : String,
briefDescription : String,
url : String,
followedPeople : List<String>,
followedCount : Int) {
    val letterSpacing =  0.5.sp
    val lineHeight = 20.sp
    Column(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Text(text = subject,
        fontWeight = FontWeight.Bold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight)
        Text(text = briefDescription,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight)
        Text(text = url,
        color = Color(0xFF207EC2),
            letterSpacing = letterSpacing,
            lineHeight = lineHeight)
        if (followedPeople.isNotEmpty()) {
            Text(text = buildAnnotatedString {
                val boldHighlightStyle = SpanStyle(color = Color.Black,
                    fontWeight = FontWeight.Bold)
                append("Followed by ")
                followedPeople.forEachIndexed { index, names ->
                    pushStyle(boldHighlightStyle)
                    append(names)
                    pop()
                    if (index < followedPeople.size -1) {
                        append(", ")
                    }
                }
                if (followedCount > 2) {
                    append(" and ")
                    pushStyle(boldHighlightStyle)
                    append("$followedCount Others")
                }
            },
                letterSpacing = letterSpacing,
                lineHeight = lineHeight
            )
        }
    }
}

@Composable
fun ButtonSection(modifier: Modifier = Modifier) {
    val minWidth = 110.dp
    val height = 30.dp
    Row(horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = modifier) {
        ButtonActions(text = "Following", icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
                .padding(horizontal = 5.dp))
        ButtonActions(text = "Message",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
                .padding(horizontal = 5.dp))
        ButtonActions(text = "Email",
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
                .padding(horizontal = 5.dp))
        ButtonActions(icon = Icons.Default.KeyboardArrowDown,
            modifier = Modifier
                .defaultMinSize(minWidth = minWidth)
                .height(height)
                .padding(horizontal = 5.dp))
    }

}

@Composable
fun ButtonActions(
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
) {
    Row(horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .border(1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp))
            .padding(6.dp)
    ) {
        if (text != null) {
            Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
        }
        if (icon != null) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
        }
    }
}

@Composable
fun HighlightSection(modifier: Modifier = Modifier,
highlights: List<ImageWithText>) {
    LazyRow(modifier = modifier.fillMaxWidth()) {
        items(highlights.size) {
            Column(verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(end = 10.dp)) {
                RoundedImage(imageSource = highlights[it].iconId,
                modifier = Modifier.height(60.dp))
                Text(text = highlights[it].text, textAlign = TextAlign.Center, overflow = TextOverflow.Ellipsis )
            }
        }
    }
}

@Composable
fun PostTabView(modifier: Modifier=Modifier,
                imageWithText: List<ImageWithText>,
                onTabSelected : (selectedIndex : Int) -> Unit) {
var selectedTabIndex by remember {
    mutableStateOf(0)
}
    val inactiveColor = Color(0xFF777777)

    TabRow(selectedTabIndex = selectedTabIndex,
    backgroundColor = Color.Transparent,
    contentColor = Color.Black,
    modifier = modifier) {
        imageWithText.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = Color.Black,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }) {
                Icon(painter = item.iconId,
                    contentDescription = item.text,
                    tint = if (selectedTabIndex == index) Color.Black else inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp))
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun PostsSection(posts : List<Painter>,
modifier: Modifier = Modifier) {
    LazyVerticalGrid(cells = GridCells.Fixed(3),
    modifier = modifier.scale(1.01f)){
        items(posts.size) {
            Image(painter = posts[it], contentDescription = "null",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(1f)
                .border(1.dp, color = Color.Green))
        }
    }

}