package org.wit.allfootballapp.presentation.login.login_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import org.wit.allfootballapp.R
import org.wit.allfootballapp.presentation.login.components.AuthOption
import org.wit.allfootballapp.presentation.login.components.MyTextField


@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit = {},
    modifier: Modifier = Modifier,
    onRegisterClick: () -> Unit = {},
) {
    val uiState by loginViewModel.uiState.collectAsState()
    val loginSuccess by loginViewModel.loginSuccess.collectAsState()

    LaunchedEffect(loginSuccess) {
        if (loginSuccess) onLoginSuccess()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.login),
                contentDescription = null,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.fillMaxWidth(0.25f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Login",
                fontWeight = FontWeight.SemiBold,
                fontSize = 30.sp
            )
        }

        MyTextField(
            text = uiState.email,
            onValueChange = { loginViewModel.onEvent(LoginUiEvent.EmailChanged(it)) },
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = if (uiState.email.isNotEmpty()) Icons.Outlined.Check else null,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        MyTextField(
            text = uiState.password,
            onValueChange = { loginViewModel.onEvent(LoginUiEvent.PasswordChanged(it)) },
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (!uiState.errorMessage.isNullOrEmpty()) {
            Text(
                text = uiState.errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = { loginViewModel.onEvent(LoginUiEvent.Login) },
            enabled = !uiState.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Login",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Don't have an account? ", fontSize = 16.sp)
            Text(
                text = "Register",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onRegisterClick() }
            )
        }
    }
}
