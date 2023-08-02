package com.idelburgo.showcaseapp.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.navArgument

interface ShowcaseAppDestination {
    val route: String
}

object Overview: ShowcaseAppDestination {
    override val route: String = "overview"
}

object Detail: ShowcaseAppDestination {
    override val route: String = "detail"
    const val idArgument: String = "pokemon_id"
    const val successArgument: String = "success_game"
    val routeWithArgs = "$route/{$successArgument}/{$idArgument}"
    val arguments = listOf(
        navArgument(successArgument) { type = NavType.BoolType },
        navArgument(idArgument) { type = NavType.IntType }
    )
}