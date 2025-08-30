package com.mkj.kinkdin.data.remote.dto.leaderboard


data class LeaderboardDto(
    val entries: List<LeaderboardEntryDto>,
    val weekRange: String,
    val lastUpdated: Long,
    val totalUsers: Int
)
