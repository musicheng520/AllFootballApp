package org.wit.allfootballapp.presentation.login.login_screen



sealed class LoginUiEvent {
    data class EmailChanged(val value: String) : LoginUiEvent()
    data class PasswordChanged(val value: String) : LoginUiEvent()
    object Login : LoginUiEvent()
}


