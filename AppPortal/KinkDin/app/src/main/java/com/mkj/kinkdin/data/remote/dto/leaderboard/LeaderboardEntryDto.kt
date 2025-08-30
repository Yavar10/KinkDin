package com.mkj.kinkdin.data.remote.dto.leaderboard


data class LeaderboardEntryDto(
    val rank: Int,
    val user: com.mkj.kinkdin.data.remote.dto.auth.UserDto,
    val points: Int,
    val easySolved: Int,
    val mediumSolved: Int,
    val hardSolved: Int,
    val totalSolved: Int,
    val weeklyProgress: Int
)