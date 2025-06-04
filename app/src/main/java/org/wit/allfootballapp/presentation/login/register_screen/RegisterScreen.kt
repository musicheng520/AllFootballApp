package org.wit.allfootballapp.presentation.login.register_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

import org.wit.allfootballapp.presentation.login.components.MyTextField


@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit = {},
    registerViewModel: RegisterViewModel = hiltViewModel(),
    onLoginClick: () -> Unit = {}, // 新增跳登录的回调
) {
    val state = registerViewModel.state.value

    if (state.isRegisterSuccess) {
        onRegisterSuccess()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceAround
    ) {
        // 用户名输入框
        MyTextField(
            text = state.username,
            onValueChange = { registerViewModel.onEvent(RegisterEvent.UsernameChanged(it)) },
            hint = "Username",
            leadingIcon = Icons.Outlined.AccountCircle,
            trailingIcon = if (state.username.isNotEmpty()) Icons.Outlined.Check else null,
            modifier = Modifier.fillMaxWidth()
        )

        // 邮箱输入框
        MyTextField(
            text = state.email,  // 这里需要你在 RegisterState 和事件中添加 email 字段
            onValueChange = { registerViewModel.onEvent(RegisterEvent.EmailChanged(it)) },
            hint = "Email",
            leadingIcon = Icons.Outlined.Email,
            trailingIcon = if (state.email.isNotEmpty()) Icons.Outlined.Check else null,
            keyboardType = KeyboardType.Email,
            modifier = Modifier.fillMaxWidth()
        )

        // 密码输入框
        MyTextField(
            text = state.password,
            onValueChange = { registerViewModel.onEvent(RegisterEvent.PasswordChanged(it)) },
            hint = "Password",
            leadingIcon = Icons.Outlined.Lock,
            isPassword = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (!state.errorMessage.isNullOrEmpty()) {
            Text(
                text = state.errorMessage,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Button(
            onClick = { registerViewModel.onEvent(RegisterEvent.RegisterClicked) },
            enabled = !state.isLoading,
            modifier = Modifier.fillMaxWidth()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp),
                    strokeWidth = 2.dp
                )
            } else {
                Text(
                    text = "Register",
                    fontSize = 17.sp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Already have an account? ", fontSize = 16.sp)
            Text(
                text = "Login",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable { onLoginClick() }
            )
        }
    }
}
