package com.example.jetpackcompose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun DrawHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp),
        contentAlignment = Alignment.Center) {
        Text(text = "Navigation Header", fontSize = 36.sp,
            fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun DrawBody(
    menuItems: List<MenuItem>,
    textStyle : TextStyle = TextStyle(fontSize = 24.sp),
    onItemClick : (MenuItem) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(menuItems) { item->
            Row(modifier = modifier
                .fillMaxWidth()
                .clickable {
                    onItemClick(item)
                }
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = item.icon,
                    contentDescription = item.contentDescription)
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = item.title,
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(1f),
                    style = textStyle)

            }
        }
    }
}

@Composable
fun DrawerNav() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        if (scaffoldState.drawerState.isClosed) {
                            scaffoldState.drawerState.open()
                        }

                    }
                }
            )
        },
        drawerContent = {
        DrawHeader()
        DrawBody(
            menuItems = listOf(
            MenuItem(id = "home",
            title = "Home",
            contentDescription = "Goto Home Screen",
            icon = Icons.Default.Home),
            MenuItem(id = "settings",
            title = "Settings",
            contentDescription = "Goto settings Screen",
            icon = Icons.Default.Settings),
            MenuItem(id = "help",
            title = "Help",
            contentDescription = "Get Help",
            icon = Icons.Default.Info),
        ),
            onItemClick = {
/*            when(it.id) {
                "settings" -> launchSettings()
            }*/
            println("Clicked on ${it.title}")
        })
    },
    drawerGesturesEnabled = scaffoldState.drawerState.isOpen//scroll nav drawer only on open
    ) {
    }
}