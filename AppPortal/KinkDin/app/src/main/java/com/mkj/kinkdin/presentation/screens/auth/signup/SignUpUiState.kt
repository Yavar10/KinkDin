package com.mkj.kinkdin.presentation.screens.auth.signup
// SignUpUiState.kt

data class SignUpUiState(
    val fullName: String = "",
    val username: String = "",
    val email: String = "",
    val leetcodeUsername: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordVisible: Boolean = false,
    val isConfirmPasswordVisible: Boolean = false,
    val agreeToTerms: Boolean = false,
    val isLoading: Boolean = false,
    val isSignUpSuccessful: Boolean = false,
    val error: String? = null,
    val fullNameError: String? = null,
    val usernameError: String? = null,
    val emailError: String? = null,
    val leetcodeUsernameError: String? = null,
    val passwordError: String? = null,
    val confirmPasswordError: String? = null
)
