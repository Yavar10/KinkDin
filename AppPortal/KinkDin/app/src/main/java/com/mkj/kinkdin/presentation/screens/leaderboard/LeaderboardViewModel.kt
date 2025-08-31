package com.mkj.kinkdin.presentation.screens.leaderboard

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mkj.kinkdin.domain.model.LeaderboardEntry
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.domain.model.UserRole
import com.mkj.kinkdin.domain.model.QuestionsSolved
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
class LeaderboardViewModel @Inject constructor(
    private val preferencesManager: PreferencesManager,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(LeaderboardUiState())
    val uiState: StateFlow<LeaderboardUiState> = _uiState.asStateFlow()

    init {
        loadLeaderboard()
        loadCurrentUser()
    }

    fun refreshLeaderboard() {
        loadLeaderboard()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            val userId = preferencesManager.getUserId()
            _uiState.value = _uiState.value.copy(currentUserId = userId)
        }
    }

    private fun loadLeaderboard() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)

            try {
                // Simulate API call delay
                delay(1500)

                // Mock leaderboard data
                val mockLeaderboard = getMockLeaderboard()
                val weekRange = "Aug 19 - Aug 25, 2025"

                _uiState.value = _uiState.value.copy(
                    leaderboard = mockLeaderboard,
                    isLoading = false,
                    weekRange = weekRange,
                    lastUpdated = System.currentTimeMillis()
                )

            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load leaderboard"
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

    private fun getMockLeaderboard(): List<LeaderboardEntry> {
        return listOf(
            LeaderboardEntry(
                rank = 1,
                user = User(
                    id = "1",
                    username = "CodeMaster",
                    email = "codemaster@example.com",
                    leetcodeUsername = "codemaster123",
                    role = UserRole.PREMIUM_USER,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 2450,
                    weeklyPoints = 380
                ),
                points = 380,
                questionsolved = QuestionsSolved(15, 12, 5, 32),
                weeklyProgress = 15
            ),
            LeaderboardEntry(
                rank = 2,
                user = User(
                    id = "2",
                    username = "AlgoQueen",
                    email = "algoqueen@example.com",
                    leetcodeUsername = "algoqueen",
                    role = UserRole.USER,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 2200,
                    weeklyPoints = 340
                ),
                points = 340,
                questionsolved = QuestionsSolved(easy = 12,medium =   10,hard =  4,total =  26),
                weeklyProgress = 12
            ),
            LeaderboardEntry(
                rank = 3,
                user = User(
                    id = "3",
                    username = "ByteWarrior",
                    email = "bytewarrior@example.com",
                    leetcodeUsername = "bytewarrior",
                    role = UserRole.USER,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 1980,
                    weeklyPoints = 310
                ),
                points = 310,
                questionsolved = QuestionsSolved(10, 8, 3, 21),
                weeklyProgress = 10
            ),
            LeaderboardEntry(
                rank = 4,
                user = User(
                    id = "4",
                    username = "DataStructGuru",
                    email = "dsqueen@example.com",
                    leetcodeUsername = "dsqueen",
                    role = UserRole.ADMIN,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 1850,
                    weeklyPoints = 285
                ),
                points = 285,
                questionsolved = QuestionsSolved(8, 7, 2, 17),
                weeklyProgress = 8
            ),
            LeaderboardEntry(
                rank = 5,
                user = User(
                    id = "5",
                    username = "RecursionKing",
                    email = "recursion@example.com",
                    leetcodeUsername = "recursionking",
                    role = UserRole.USER,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 1720,
                    weeklyPoints = 260
                ),
                points = 260,
                questionsolved = QuestionsSolved(7, 6, 2, 15),
                weeklyProgress = 7
            )
        )
    }
}