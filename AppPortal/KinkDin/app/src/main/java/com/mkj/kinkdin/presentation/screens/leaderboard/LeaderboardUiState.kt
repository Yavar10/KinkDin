package com.mkj.kinkdin.presentation.screens.leaderboard

import com.mkj.kinkdin.domain.model.LeaderboardEntry

data class LeaderboardUiState(
    val leaderboard: List<LeaderboardEntry> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val weekRange: String = "",
    val currentUserId: String? = null,
    val currentUserRank: Int? = null,
    val lastUpdated: Long = 0L,
)