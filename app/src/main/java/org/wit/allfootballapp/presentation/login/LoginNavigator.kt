package org.wit.allfootballapp.presentation.login

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.repository.UserRepository
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.presentation.login.choose.ChooseTeamScreen
import org.wit.allfootballapp.presentation.login.login_screen.LoginScreen
import org.wit.allfootballapp.presentation.login.login_screen.LoginViewModel
import org.wit.allfootballapp.presentation.login.register_screen.RegisterScreen

sealed class LoginDestinations(val route: String) {
    object Login : LoginDestinations("login")
    object Register : LoginDestinations("register")
    object SelectTeam : LoginDestinations("select_team")
}

@Composable
fun LoginNavigator(
    onLoginSuccess: (teamId: Int) -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel(),  // Hilt 注入 ViewModel
    rootNavController: NavController
) {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()

    val localUserManager = loginViewModel.localUserManager
    val userRepository = loginViewModel.userRepository

    NavHost(
        navController = navController,
        startDestination = LoginDestinations.Login.route
    ) {
        composable(LoginDestinations.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    coroutineScope.launch {
                        val userId = localUserManager.readUserId().firstOrNull()
                        if (userId != null) {
                            val user = userRepository.getUserById(userId)
                            if (user?.teamId != null) {
                                // user.teamId 是 Int?，确保非空后传入
                                onLoginSuccess(user.teamId)
                            } else {
                                navController.navigate(LoginDestinations.SelectTeam.route) {
                                    popUpTo(LoginDestinations.Login.route) { inclusive = true }
                                }
                            }
                        } else {
                            // userId 不存在的情况，也跳转选队页或其他处理
                            navController.navigate(LoginDestinations.SelectTeam.route) {
                                popUpTo(LoginDestinations.Login.route) { inclusive = true }
                            }
                        }
                    }
                },
                onRegisterClick = {
                    navController.navigate(LoginDestinations.Register.route)
                }
            )
        }

        composable(LoginDestinations.Register.route) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(LoginDestinations.Login.route) {
                        popUpTo(LoginDestinations.Register.route) { inclusive = true }
                    }
                },
                onLoginClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(LoginDestinations.SelectTeam.route) {
            ChooseTeamScreen(
                navController = rootNavController,
                onTeamSelected = {
                    coroutineScope.launch {
                        val userId = localUserManager.readUserId().firstOrNull()
                        if (userId != null) {
                            val user = userRepository.getUserById(userId)
                            user?.teamId?.let { teamId ->
                                onLoginSuccess(teamId)
                            }
                        }
                    }
                }
            )
        }
    }
}

