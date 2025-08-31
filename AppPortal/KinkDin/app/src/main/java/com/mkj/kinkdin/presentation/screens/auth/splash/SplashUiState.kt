package com.mkj.kinkdin.presentation.screens.auth.splash

data class SplashUiState(
    val isCheckingAuth: Boolean = true,
    val isLoggedIn: Boolean = false,
    val loadingMessage: String = "Checking authentication...",
    val error: String? = null,
    val appVersion: String = "1.0.0"
)