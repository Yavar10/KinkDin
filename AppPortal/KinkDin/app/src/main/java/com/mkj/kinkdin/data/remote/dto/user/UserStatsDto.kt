package com.mkj.kinkdin.data.remote.dto.user

data class UserStatsDto(
    val userId: String,
    val leetcodeUsername: String,
    val totalSolved: Int,
    val easySolved: Int,
    val mediumSolved: Int,
    val hardSolved: Int,
    val acceptanceRate: Double,
    val ranking: Int?,
    val contributionPoints: Int,
    val reputation: Int,
    val weeklyProgress: Int,
    val monthlyProgress: Int,
    val streakDays: Int,
    val maxStreak: Int,
    val lastUpdated: Long,
    val submissionCalendar: Map<String, Int>? = null,
)