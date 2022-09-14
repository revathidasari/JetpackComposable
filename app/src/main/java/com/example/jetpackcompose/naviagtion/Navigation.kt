package com.example.jetpackcompose.naviagtion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route ) {
        composable(route = Screen.MainScreen.route) {
            MainScreenPage(navController = navController)
        }

        composable(route = Screen.DetailsScreen.route + "/{name}",
            /*for mandatory text mention as /{name},
            *   multiple arguments /{name}/{age}
            * to consider default ?name={name}
             */

        arguments = listOf(
            navArgument("name") {
                type = NavType.StringType
                defaultValue = "Revathi"
                nullable = true
            }
        )) { entry ->
            DetailsScreenPage(text = entry.arguments?.getString("name") ?: "")

        }
    }
}