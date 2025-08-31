package com.mkj.kinkdin.presentation.screens.profile

data class EditProfileUiState(
    val fullName: String = "",
    val username: String = "",
    val leetcodeUsername: String = "",
    val profilePictureUrl: String = "",
    val isLoading: Boolean = false,
    val isSaveSuccessful: Boolean = false,
    val error: String? = null,
    val fullNameError: String? = null,
    val usernameError: String? = null,
    val leetcodeUsernameError: String? = null,
    val profilePictureError: String? = null
)