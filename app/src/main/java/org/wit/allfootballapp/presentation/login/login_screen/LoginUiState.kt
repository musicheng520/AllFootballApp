package org.wit.allfootballapp.presentation.login.login_screen


data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

