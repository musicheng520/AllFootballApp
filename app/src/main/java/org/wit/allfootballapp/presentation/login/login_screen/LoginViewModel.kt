package org.wit.allfootballapp.presentation.login.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
     val userRepository: UserRepository,
    val localUserManager: LocalUserManager
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess = _loginSuccess.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> _uiState.update {
                it.copy(email = event.value, errorMessage = null)
            }

            is LoginUiEvent.PasswordChanged -> _uiState.update {
                it.copy(password = event.value, errorMessage = null)
            }

            is LoginUiEvent.Login -> login()
        }
    }

    private fun login() {
        val state = _uiState.value
        if (state.email.isBlank() || state.password.isBlank()) {
            _uiState.update { it.copy(errorMessage = "Email and password cannot be empty") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val user = userRepository.getUserByCredentials(state.email, state.password)
                if (user == null) {
                    _uiState.update { it.copy(errorMessage = "Incorrect email or password") }
                } else {
                    // ✅ 保存登录状态
                    localUserManager.saveLoginStatus(true)
                    // ✅ 保存 userId
                    localUserManager.saveUserId(user.userId)

                    _loginSuccess.value = true
                }
            } catch (e: Exception) {
                _uiState.update { it.copy(errorMessage = "Login failed: ${e.message}") }
            }

            _uiState.update { it.copy(isLoading = false) }
        }
    }
}
