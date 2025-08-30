package com.mkj.kinkdin.presentation.screens.auth.login

// LoginUiState.kt

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val isPasswordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isLoginSuccessful: Boolean = false,
    val error: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null
)