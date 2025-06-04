package org.wit.allfootballapp.presentation.login.register_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.wit.allfootballapp.domain.manager.LocalUserManager
import org.wit.allfootballapp.domain.model.user.User
import org.wit.allfootballapp.domain.repository.UserRepository
import javax.inject.Inject
import androidx.compose.runtime.State





@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val localUserManager: LocalUserManager
) : ViewModel() {

    private val _state = mutableStateOf(RegisterState())
    val state: State<RegisterState> = _state

    fun onEvent(event: RegisterEvent) {
        when (event) {
            is RegisterEvent.UsernameChanged -> {
                _state.value = _state.value.copy(username = event.username, errorMessage = null)
            }
            is RegisterEvent.EmailChanged -> {
                _state.value = _state.value.copy(email = event.email, errorMessage = null)
            }
            is RegisterEvent.PasswordChanged -> {
                _state.value = _state.value.copy(password = event.password, errorMessage = null)
            }
            RegisterEvent.RegisterClicked -> {
                register()
            }
        }
    }

    private fun register() {
        val currentState = _state.value

        if (currentState.username.isBlank() ||
            currentState.email.isBlank() ||
            currentState.password.isBlank()
        ) {
            _state.value = _state.value.copy(errorMessage = "All fields are required")
            return
        }

        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true, errorMessage = null)

            try {
                val existingUser = userRepository.getUserByName(currentState.username)
                if (existingUser != null) {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        errorMessage = "Username already exists"
                    )
                } else {
                    userRepository.upsertUser(
                        User(
                            username = currentState.username,
                            email = currentState.email,
                            password = currentState.password,
                            teamId = null // 或者 0，取决于你的 User 定义
                        )
                    )

                    localUserManager.saveLoginStatus(true)

                    _state.value = _state.value.copy(
                        isLoading = false,
                        isRegisterSuccess = true,
                        errorMessage = null
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = "Registration failed: ${e.message}"
                )
            }
        }
    }
}
