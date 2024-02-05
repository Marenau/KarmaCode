package com.corylab.karmacode.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.corylab.karmacode.ui.screen.CalculationScreen
import com.corylab.karmacode.ui.screen.MatrixScreen

@Composable
fun Navigation(applicationContext: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "calculation") {
        composable(route = "calculation") {
            CalculationScreen(navController = navController)
        }
        composable(
            route = "matrix/{data}",
            arguments = listOf(navArgument("data") { type = NavType.StringType })
        ) {
            val data = it.arguments?.getString("data")!!
            MatrixScreen(navController = navController, data = data)
        }
    }
}