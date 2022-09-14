package com.example.jetpackcompose

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.selects.select

@Composable
fun NavigationBottom(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            ScreenMessage(message = "Home Screen")
        }
        composable(route = "work") {
            ScreenMessage(message = "Work Screen")
        }
        composable(route = "settings") {
            ScreenMessage(message = "Settings Screen")
        }
    }
}

@Composable
fun BottomNavigationBar(
    items : List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick : (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
            icon = {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    if (item.badgeCount >0) {
                        BadgedBox(
                            badge = {
                                Text(text = item.badgeCount.toString(),
                                    color = Color.White,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color.Red))
                            },
                        ) {
                            Icon(imageVector = item.icon, contentDescription = item.name)   
                        }
                    } else  {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                    }
                    if (selected) {
                        Text(text = item.name, textAlign = TextAlign.Center, fontSize = 14.sp)
                    }
                }

            })
        }
    }

}

@Composable
fun ScreenMessage(message : String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = message, textAlign = TextAlign.Center)
    }
}