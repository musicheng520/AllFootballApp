package org.wit.allfootballapp.presentation.login.register_screen



data class RegisterState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val errorMessage: String? = null,
    val isLoading: Boolean = false,
    val isRegisterSuccess: Boolean = false
)


sealed class RegisterEvent {
    data class UsernameChanged(val username: String) : RegisterEvent()
    data class EmailChanged(val email: String) : RegisterEvent()
    data class PasswordChanged(val password: String) : RegisterEvent()
    object RegisterClicked : RegisterEvent()
}

