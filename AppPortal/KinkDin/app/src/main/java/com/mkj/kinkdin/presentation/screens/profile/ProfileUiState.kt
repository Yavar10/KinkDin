package com.mkj.kinkdin.presentation.screens.profile

import com.mkj.kinkdin.domain.model.User

data class ProfileUiState(
    val user: User? = null,
    val isCurrentUser: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val isRefreshing: Boolean = false
)
