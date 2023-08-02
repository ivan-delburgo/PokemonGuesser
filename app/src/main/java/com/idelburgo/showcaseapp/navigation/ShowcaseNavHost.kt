package com.idelburgo.showcaseapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.idelburgo.showcaseapp.ui.datail.DetailScreen
import com.idelburgo.showcaseapp.ui.overview.MainGameScreen

@Composable
fun ShowcaseNavhost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Overview.route,
        modifier = modifier
    ) {
        composable(route = Overview.route) {
            MainGameScreen(
                onGameFinished = { success, id ->
                    navController.navigateSingleTopTo( "${Detail.route}/${success}/${id}")
                }
            )
        }
        composable(
            route = Detail.routeWithArgs,
            arguments = Detail.arguments
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt(Detail.idArgument,0) ?: 0
            val success = navBackStackEntry.arguments?.getBoolean(Detail.successArgument,false) ?: false
            DetailScreen(
                item = id,
                success = success, onReset = {
                    navController.navigateSingleTopTo(Overview.route)
                })
        }

    }
}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }