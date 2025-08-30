package com.mkj.kinkdin.presentation.screens.auth.splash


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.kinkdin.util.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel1 @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            try {
                val isLoggedIn = preferencesManager.isLoggedIn()
                _uiState.value = _uiState.value.copy(
                    isCheckingAuth = false,
                    isLoggedIn = isLoggedIn
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isCheckingAuth = false,
                    isLoggedIn = false,
                    error = e.message
                )
            }
        }
    }
}


@HiltViewModel
class SplashViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
) : ViewModel() {

    private val _uiState = MutableStateFlow(SplashUiState())
    val uiState: StateFlow<SplashUiState> = _uiState.asStateFlow()

    init {
        checkAuthStatus()
    }

    private fun checkAuthStatus() {
        viewModelScope.launch {
            try {
                // Update loading message
                _uiState.value = _uiState.value.copy(
                    loadingMessage = "Initializing..."
                )

                // Simulate app initialization delay
                delay(800)

                // Update loading message
                _uiState.value = _uiState.value.copy(
                    loadingMessage = "Checking authentication..."
                )

                // Simulate auth check delay
                delay(500)

                // Check if user is logged in
                val token = preferencesManager.getAuthToken()
                val isLoggedIn = !token.isNullOrBlank()

                // If logged in, verify token validity (in real app)
                if (isLoggedIn) {
                    _uiState.value = _uiState.value.copy(
                        loadingMessage = "Verifying credentials..."
                    )
                    delay(600)

                    // In real app, you would validate the token with your backend
                    val isTokenValid = validateToken(token)

                    if (!isTokenValid) {
                        // Clear invalid token
                        preferencesManager.clearAll()
                        _uiState.value = _uiState.value.copy(
                            isCheckingAuth = false,
                            isLoggedIn = false,
                            loadingMessage = ""
                        )
                        return@launch
                    }
                }

                // Final loading message
                _uiState.value = _uiState.value.copy(
                    loadingMessage = if (isLoggedIn) "Loading dashboard..." else "Redirecting to login..."
                )

                delay(400)

                // Update final state
                _uiState.value = _uiState.value.copy(
                    isCheckingAuth = false,
                    isLoggedIn = isLoggedIn,
                    loadingMessage = ""
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isCheckingAuth = false,
                    isLoggedIn = false,
                    error = "Failed to initialize: ${e.message}",
                    loadingMessage = ""
                )
            }
        }
    }

    private suspend fun validateToken(token: String?): Boolean {
        // In a real app, you would make an API call to validate the token
        // For now, just simulate the validation
        delay(300)

        // Mock validation - consider token valid if it's not empty
        // In real implementation, you would:
        // 1. Make API call to validate token
        // 2. Check token expiration
        // 3. Refresh token if needed
        return !token.isNullOrBlank()
    }

    fun retryInitialization() {
        _uiState.value = SplashUiState() // Reset state
        checkAuthStatus()
    }
}