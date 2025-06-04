package org.wit.allfootballapp.presentation.onboarding.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.wit.allfootballapp.presentation.onboarding.splash.SplashViewModel

/*@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isLoading, uiState.isLoggedIn, uiState.teamId) {
        if (!uiState.isLoading) {
            if (uiState.isLoggedIn) {
                navController.navigate("main_screen/${uiState.teamId ?: 529}") {
                    popUpTo("splash_screen") { inclusive = true }
                }
            } else {
                navController.navigate("choose_team_screen") {
                    popUpTo("splash_screen") { inclusive = true }
                }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}*/

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isLoading, uiState.isLoggedIn, uiState.teamId) {
        if (!uiState.isLoading) {
            if (!uiState.isLoggedIn) {
                // 未登录，跳转登录页面
                navController.navigate("login_screen") {
                    popUpTo("splash_screen") { inclusive = true }
                }
            } else {
                // 登录了，判断teamId
                if (uiState.teamId == null) {
                    navController.navigate("choose_team_screen") {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                } else {
                    navController.navigate("main_screen/${uiState.teamId}") {
                        popUpTo("splash_screen") { inclusive = true }
                    }
                }
            }
        }
    }

/*    if (!uiState.isLoading) {
        if (!uiState.isLoggedIn) {
            navController.navigate("login_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        } else {
            // 登录了直接跳 login_screen，让 LoginNavigator 里负责判断 teamId 跳转选队或主界面
            navController.navigate("login_screen") {
                popUpTo("splash_screen") { inclusive = true }
            }
        }
    }*/


    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}


