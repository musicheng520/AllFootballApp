package org.wit.allfootballapp.presentation.news.news_navigator

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.model.news.Article
import org.wit.allfootballapp.presentation.news.details.DetailScreen
import org.wit.allfootballapp.presentation.news.details.DetailsEvent
import org.wit.allfootballapp.presentation.news.details.DetailsViewModel
import org.wit.allfootballapp.presentation.news.news_navigator.TabScreen
import org.wit.allfootballapp.presentation.news.nvgraph.Route


@Composable
fun NewsNavigator() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val backstackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        // 底部导航栏去掉了，不用 bottomBar
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Route.TabScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            // 主Tab屏幕，包含Home/Search/Bookmark三个子页面
            composable(Route.TabScreen.route) {
                TabScreen(
                    onNavigateToDetails = { article ->
                        // 进入详情页
                        coroutineScope.launch {
                            navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                            navController.navigate(Route.DetailsScreen.route)
                        }
                    }
                )
            }

            // 详情页
            composable(Route.DetailsScreen.route) {
                val viewModel: DetailsViewModel = hiltViewModel()

                // 监听并弹出Toast消息
                LaunchedEffect(viewModel.sideEffect) {
                    viewModel.sideEffect?.let { msg ->
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        viewModel.onEvent(DetailsEvent.RemoveSideEffect)
                    }
                }

                // 从SavedStateHandle获取article参数
                val article = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Article>("article")

                article?.let {
                    DetailScreen(
                        article = it,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() }
                    )
                }
            }
        }
    }
}
