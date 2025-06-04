/*package org.wit.allfootballapp.presentation.news.nvgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.paging.compose.collectAsLazyPagingItems
import org.wit.allfootballapp.presentation.news.onboarding.OnBoardingScreen
import org.wit.allfootballapp.presentation.news.onboarding.OnBoardingViewModel
import org.wit.newsapp.presentation.news_navigator.NewsNavigator


@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(navController=navController,startDestination=startDestination){
        navigation(
            route= Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ){
            composable(
                route= Route.OnBoardingScreen.route
            ){
                val viewModel : OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event =viewModel::onEvent
                )
            }
        }

        navigation(
            route= Route.NewsNavigation.route,
            startDestination= Route.NewsNavigatorScreen.route
        ) {
            composable(
                route= Route.NewsNavigatorScreen.route
            ) {
                NewsNavigator()

            }
        }
    }
}*/
