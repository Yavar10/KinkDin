package com.mkj.kinkdin.presentation.screens.auth.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.kinkdin.domain.usecase.auth.SignUpUseCase
import com.mkj.kinkdin.util.Resource
import com.mkj.kinkdin.util.ValidationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SignUpUiState())
    val uiState: StateFlow<SignUpUiState> = _uiState.asStateFlow()

    fun updateFullName(fullName: String) {
        _uiState.value = _uiState.value.copy(
            fullName = fullName,
            fullNameError = null
        )
    }

    fun updateUsername(username: String) {
        _uiState.value = _uiState.value.copy(
            username = username,
            usernameError = null
        )
    }

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null
        )
    }

    fun updateLeetcodeUsername(leetcodeUsername: String) {
        _uiState.value = _uiState.value.copy(
            leetcodeUsername = leetcodeUsername,
            leetcodeUsernameError = null
        )
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null
        )
    }

    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(
            confirmPassword = confirmPassword,
            confirmPasswordError = null
        )
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isPasswordVisible = !_uiState.value.isPasswordVisible
        )
    }

    fun toggleConfirmPasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isConfirmPasswordVisible = !_uiState.value.isConfirmPasswordVisible
        )
    }

    fun updateAgreeToTerms(agree: Boolean) {
        _uiState.value = _uiState.value.copy(agreeToTerms = agree)
    }

    fun signUp() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
                isSignUpSuccessful = false
            )

            val result = signUpUseCase(
                username = _uiState.value.username,
                email = _uiState.value.email,
                password = _uiState.value.password,
                leetcodeUsername = _uiState.value.leetcodeUsername
            )

            when (result) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isSignUpSuccessful = true,
                        error = null
                    )
                }
                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.message,
                        isSignUpSuccessful = false
                    )
                }
                is Resource.Loading -> {
                    // Already handled above
                }
            }
        }
    }

    private fun validateInputs(): Boolean {
        val state = _uiState.value
        var isValid = true

        val fullNameValidation = ValidationUtils.validateFullName(state.fullName)
        if (!fullNameValidation.isValid) {
            _uiState.value = _uiState.value.copy(fullNameError = fullNameValidation.errorMessage)
            isValid = false
        }

        val usernameValidation = ValidationUtils.validateUsername(state.username)
        if (!usernameValidation.isValid) {
            _uiState.value = _uiState.value.copy(usernameError = usernameValidation.errorMessage)
            isValid = false
        }

        val emailValidation = ValidationUtils.validateEmail(state.email)
        if (!emailValidation.isValid) {
            _uiState.value = _uiState.value.copy(emailError = emailValidation.errorMessage)
            isValid = false
        }

        val leetcodeUsernameValidation = ValidationUtils.validateLeetcodeUsername(state.leetcodeUsername)
        if (!leetcodeUsernameValidation.isValid) {
            _uiState.value = _uiState.value.copy(leetcodeUsernameError = leetcodeUsernameValidation.errorMessage)
            isValid = false
        }

        val passwordValidation = ValidationUtils.validatePassword(state.password)
        if (!passwordValidation.isValid) {
            _uiState.value = _uiState.value.copy(passwordError = passwordValidation.errorMessage)
            isValid = false
        }

        val confirmPasswordValidation = ValidationUtils.validatePasswordConfirmation(state.password, state.confirmPassword)
        if (!confirmPasswordValidation.isValid) {
            _uiState.value = _uiState.value.copy(confirmPasswordError = confirmPasswordValidation.errorMessage)
            isValid = false
        }

        if (!state.agreeToTerms) {
            _uiState.value = _uiState.value.copy(error = "Please agree to the terms and conditions")
            isValid = false
        }

        return isValid
    }
}
