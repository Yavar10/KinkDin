package com.mkj.kinkdin.presentation.screens.auth.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.kinkdin.domain.usecase.auth.LoginUseCase
import com.mkj.kinkdin.util.Resource
import com.mkj.kinkdin.util.ValidationUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(
            email = email,
            emailError = null
        )
    }

    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(
            password = password,
            passwordError = null
        )
    }

    fun togglePasswordVisibility() {
        _uiState.value = _uiState.value.copy(
            isPasswordVisible = !_uiState.value.isPasswordVisible
        )
    }

    fun login() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                error = null,
                isLoginSuccessful = false
            )

            val result = loginUseCase(
                email = _uiState.value.email,
                password = _uiState.value.password
            )

            when (result) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        isLoginSuccessful = true,
                        error = null
                    )
                }
                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        error = result.message,
                        isLoginSuccessful = false
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

        val emailValidation = ValidationUtils.validateEmail(state.email)
        if (!emailValidation.isValid) {
            _uiState.value = _uiState.value.copy(emailError = emailValidation.errorMessage)
            isValid = false
        }

        val passwordValidation = ValidationUtils.validatePassword(state.password)
        if (!passwordValidation.isValid) {
            _uiState.value = _uiState.value.copy(passwordError = passwordValidation.errorMessage)
            isValid = false
        }

        return isValid
    }
}
