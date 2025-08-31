package com.mkj.kinkdin.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditProfileViewModel @Inject constructor(
    // Inject repositories here when ready
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditProfileUiState())
    val uiState: StateFlow<EditProfileUiState> = _uiState.asStateFlow()

    init {
        loadCurrentUserData()
    }

    private fun loadCurrentUserData() {
        // Load current user data for editing
        _uiState.value = _uiState.value.copy(
            fullName = "John Doe",
            username = "johndoe",
            leetcodeUsername = "johndoe123",
            profilePictureUrl = ""
        )
    }

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

    fun updateLeetcodeUsername(leetcodeUsername: String) {
        _uiState.value = _uiState.value.copy(
            leetcodeUsername = leetcodeUsername,
            leetcodeUsernameError = null
        )
    }

    fun updateProfilePicture(profilePictureUrl: String) {
        _uiState.value = _uiState.value.copy(
            profilePictureUrl = profilePictureUrl,
            profilePictureError = null
        )
    }

    fun saveProfile() {
        if (!validateInputs()) return

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                // Simulate API call delay
                delay(2000)

                // Mock successful save
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    isSaveSuccessful = true
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to save profile"
                )
            }
        }
    }

    private fun validateInputs(): Boolean {
        val state = _uiState.value
        var isValid = true

        if (state.fullName.isBlank()) {
            _uiState.value = _uiState.value.copy(fullNameError = "Full name is required")
            isValid = false
        }

        if (state.username.isBlank()) {
            _uiState.value = _uiState.value.copy(usernameError = "Username is required")
            isValid = false
        }

        if (state.leetcodeUsername.isBlank()) {
            _uiState.value = _uiState.value.copy(leetcodeUsernameError = "LeetCode username is required")
            isValid = false
        }

        return isValid
    }
}