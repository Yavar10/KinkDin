package com.mkj.kinkdin.presentation.screens.settings


data class SettingsUiState(
    val notificationsEnabled: Boolean = true,
    val selectedTheme: String = "Dark",
    val isLoading: Boolean = false,
    val error: String? = null
)