package com.mkj.kinkdin.domain.model


data class LeetcodeStats(
    val username: String,
    val totalSolved: Int,
    val easySolved: Int,
    val mediumSolved: Int,
    val hardSolved: Int,
    val acceptanceRate: Double,
    val ranking: Int?,
    val reputation: Int,
    val contributionPoints: Int,
    val badges: List<LeetcodeBadge> = emptyList(),
    val recentSubmissions: List<LeetcodeSubmission> = emptyList(),
    val lastUpdated: Long = System.currentTimeMillis()
)

data class LeetcodeBadge(
    val id: String,
    val name: String,
    val description: String,
    val iconUrl: String,
    val earnedDate: Long
)

data class LeetcodeSubmission(
    val problemTitle: String,
    val problemId: String,
    val difficulty: String,
    val status: String, // "Accepted", "Wrong Answer", etc.
    val runtime: String?,
    val memory: String?,
    val timestamp: Long,
    val language: String
)