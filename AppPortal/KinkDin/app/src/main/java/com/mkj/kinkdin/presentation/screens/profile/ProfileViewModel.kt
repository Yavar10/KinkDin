package com.mkj.kinkdin.presentation.screens.profile

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.domain.model.UserRole
import com.mkj.kinkdin.util.PreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun loadUserProfile(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                // Simulate API call delay
                delay(1000)

                // Check if this is the current user
                val currentUserId = preferencesManager.getUserId()
                val isCurrentUser = userId == currentUserId

                // Mock user data
                val user = getMockUser(userId)

                _uiState.value = _uiState.value.copy(
                    user = user,
                    isCurrentUser = isCurrentUser,
                    isLoading = false
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load profile"
                )
            }
        }
    }

    fun refreshUserStats(userId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isRefreshing = true)

            try {
                // Simulate API call delay
                delay(1500)

                // Reload the user data (in real app, this would sync with Leetcode API)
                loadUserProfile(userId)

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isRefreshing = false,
                    error = e.message ?: "Failed to refresh stats"
                )
            }
        }
    }

    fun openExternalLeetcodeProfile(username: String) {
        try {
            val leetcodeUrl = "https://leetcode.com/$username"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(leetcodeUrl))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (e: Exception) {
            // Handle error
        }
    }

    private fun getMockUser(userId: String): User {
        // Return mock user data based on userId
        return when (userId) {
            "1" -> User(
                id = "1",
                username = "CodeMaster",
                email = "codemaster@example.com",
                leetcodeUsername = "codemaster123",
                role = UserRole.PREMIUM_USER,
                joinedDate = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000), // 30 days ago
                totalPoints = 2450,
                weeklyPoints = 380,
                currentRank = 1
            )
            "2" -> User(
                id = "2",
                username = "AlgoQueen",
                email = "algoqueen@example.com",
                leetcodeUsername = "algoqueen",
                role = UserRole.USER,
                joinedDate = System.currentTimeMillis() - (45L * 24 * 60 * 60 * 1000), // 45 days ago
                totalPoints = 2200,
                weeklyPoints = 340,
                currentRank = 2
            )
            else -> User(
                id = userId,
                username = "User$userId",
                email = "user$userId@example.com",
                leetcodeUsername = "user$userId",
                role = UserRole.USER,
                joinedDate = System.currentTimeMillis() - (60L * 24 * 60 * 60 * 1000), // 60 days ago
                totalPoints = 1500,
                weeklyPoints = 250,
                currentRank = 10
            )
        }
    }
}
