package org.wit.allfootballapp.presentation.navgraph

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.wit.allfootballapp.presentation.login.LoginDestinations
import org.wit.allfootballapp.presentation.login.LoginNavigator
import org.wit.allfootballapp.presentation.login.choose.ChooseTeamScreen
import org.wit.allfootballapp.presentation.login.choose.ChooseTeamViewModel
import org.wit.allfootballapp.presentation.login.login_screen.LoginViewModel
import org.wit.allfootballapp.presentation.onboarding.splash.SplashScreen


@Composable
fun AppNavGraph(
    startDestination: String = "splash_screen",
    onLoginSuccess: (teamId: Int) -> Unit = {}
)  {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
        // 启动页，通常检查登录状态，跳转登录或主界面
        composable("splash_screen") {
            SplashScreen(navController)
        }

        // 登录相关导航
        composable("login_screen") {
            LoginNavigator(
                rootNavController = navController,
                onLoginSuccess = { teamId ->
                    // 登录成功，跳转主界面
                    navController.navigate("main_screen/$teamId") {
                        // 清除登录和选队相关页面，避免返回
                        popUpTo("login_screen") { inclusive = true }
                    }
                }
            )
        }

        composable("choose_team_screen") {
            ChooseTeamScreen(
                navController
            ) { }
        }



        // 主界面，接收teamId参数
        composable(
            route = "main_screen/{teamId}",
            arguments = listOf(navArgument("teamId") { type = NavType.IntType })
        ) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getInt("teamId") ?: 529
            MainScreen(teamId = teamId, rootNavController = navController)
        }
    }
}


/*@Composable
fun AppNavGraph(startDestination: String = "splash_screen") {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("login_screen") {
            val viewModel: LoginViewModel = hiltViewModel()
            val localUserManager = viewModel.localUserManager
            var teamId by remember { mutableStateOf<Int?>(null) }
            val coroutineScope = rememberCoroutineScope()

            // 读取 teamId 只执行一次
            LaunchedEffect(Unit) {
                val savedTeamId = localUserManager.readTeamId().firstOrNull()
                if (savedTeamId != null) {
                    navController.navigate("main_screen/$savedTeamId") {
                        popUpTo("login_screen") { inclusive = true }
                    }
                } else {
                    teamId = 0 // 标记为“没有 teamId，可以显示 LoginNavigator”
                }
            }

            if (teamId == 0) {
                LoginNavigator(navController = navController) { selectedTeamId ->
                    navController.navigate("main_screen/$selectedTeamId") {
                        popUpTo("login_screen") { inclusive = true }
                    }
                }
            }
        }

        composable(
            route = "main_screen/{teamId}",
            arguments = listOf(navArgument("teamId") { type = NavType.IntType })
        ) { backStackEntry ->
            val teamId = backStackEntry.arguments?.getInt("teamId") ?: 0
            MainScreen(teamId = teamId, rootNavController = navController)
        }
    }
}*/



