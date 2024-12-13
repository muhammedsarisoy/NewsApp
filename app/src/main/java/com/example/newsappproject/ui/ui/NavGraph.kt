package com.example.newsappproject.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsappproject.ui.ui.DetailScreen
import com.example.newsappproject.ui.ui.HomeScreen
import com.example.newsappproject.ui.ui.HomeViewModel

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    NavHost(
        navController = navHostController,
        startDestination = "HomeScreen"
    ) {
        composable("HomeScreen") {
            HomeScreen(
                modifier = modifier,
                viewModel = viewModel,
                navHostController = navHostController
            )
        }
        composable(
            route = "DetailScreen/{newsTitle}",
            arguments = listOf(navArgument("newsTitle") { type = NavType.StringType })
        ) { backStackEntry ->
            val newsTitle = backStackEntry.arguments?.getString("newsTitle")
            DetailScreen(
                modifier = modifier,
                viewModel = viewModel,
                navHostController = navHostController,
                newsId = newsTitle ?: ""
            )
        }
    }
}
