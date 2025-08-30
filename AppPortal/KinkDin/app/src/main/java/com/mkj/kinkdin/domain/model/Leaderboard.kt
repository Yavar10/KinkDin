package com.mkj.kinkdin.domain.model

data class Leaderboard(
    val entries: List<LeaderboardEntry>,
    val weekRange: String,
    val lastUpdated: Long,
    val totalUsers: Int
)
