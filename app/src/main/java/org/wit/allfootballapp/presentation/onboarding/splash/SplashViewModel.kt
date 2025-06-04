package org.wit.allfootballapp.presentation.onboarding.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.domain.repository.UserRepository
import javax.inject.Inject


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val localUserManager: LocalUserManager,
    private val userRepository: UserRepository  // Room的仓库
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val isLoggedIn = localUserManager.readLoginStatus().firstOrNull() ?: false

            if (!isLoggedIn) {
                // 未登录，直接更新状态，不用查teamId
                _uiState.value = SplashUiState(isLoggedIn = false, isLoading = false)
                return@launch
            }

            // 登录了，读取 userId
            val userId = localUserManager.readUserId().firstOrNull()
            if (userId == null) {
                // userId 不存在，视为未登录
                _uiState.value = SplashUiState(isLoggedIn = false, isLoading = false)
                return@launch
            }

            // 用 userId 去数据库查用户，拿teamId
            val user = userRepository.getUserById(userId)  // 挂起函数
            val teamId = user?.teamId

            _uiState.value = SplashUiState(
                isLoggedIn = true,
                teamId = teamId,
                isLoading = false
            )
        }
    }
}


data class SplashUiState(
    val isLoggedIn: Boolean = false,
    val teamId: Int? = null,
    val isLoading: Boolean = true
)





