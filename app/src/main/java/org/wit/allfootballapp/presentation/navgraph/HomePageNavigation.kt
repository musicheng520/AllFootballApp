package org.wit.allfootballapp.presentation.navgraph

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.wit.allfootballapp.presentation.fixtures.FixtureScreen

import org.wit.allfootballapp.presentation.home_page.component.HomeScreen
import org.wit.allfootballapp.presentation.home_page.worker.player_detail.PlayerDetailScreen
import org.wit.allfootballapp.presentation.navgraph.components.BottomBar
import org.wit.allfootballapp.presentation.news.news_navigator.NewsNavigator


@Composable
fun MainScreen(teamId: Int, rootNavController: NavController) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController,teamId)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home/$teamId",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("news") {
                NewsNavigator()
            }
            composable("home/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull() ?: 0
                HomeScreen(teamId,navController,rootNavController)
            }
            composable("matches/{teamId}") { backStackEntry ->
                val teamId = backStackEntry.arguments?.getString("teamId")?.toIntOrNull() ?: 0
                FixtureScreen(teamId, onTeamClicked = {id->
                    rootNavController.navigate("main_screen/$id") {
                        popUpTo("home/$teamId") { inclusive = true }
                    }
                })
            }


            composable("playerDetail/{playerId}/{seasonId}") { backStackEntry ->
                val playerId = backStackEntry.arguments?.getString("playerId")?.toIntOrNull() ?: 0
                val seasonId = backStackEntry.arguments?.getString("seasonId")?.toIntOrNull() ?: 0
                PlayerDetailScreen(playerId = playerId,seasonId = seasonId,navController)
            }

        }
    }
}



