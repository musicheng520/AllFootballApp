package org.wit.allfootballapp.presentation.navgraph.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.SportsSoccer
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Newspaper
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(navController: NavHostController, teamId: Int) {
    val items = listOf("news", "home", "matches")
    val icons = listOf(Icons.Filled.Newspaper, Icons.Filled.Home, Icons.Filled.SportsSoccer)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    NavigationBar {
        items.forEachIndexed { index, screen ->
            // 拼接带参数的完整路由（除了news不带参数）
            val route = when(screen) {
                "home" -> "home/$teamId"
                "matches" -> "matches/$teamId"
                else -> screen
            }

            // 判断选中时用 startsWith 兼容带参数路由
            val selected = currentRoute.startsWith(screen)

            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = null) },
                selected = selected,
                onClick = {
                    if (currentRoute != route) {
                        navController.navigate(route) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
