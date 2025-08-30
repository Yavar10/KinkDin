package com.mkj.kinkdin.data.remote.dto.leetcode

data class LeetcodeStatsDto(
    val username: String,
    val totalSolved: Int,
    val easySolved: Int,
    val mediumSolved: Int,
    val hardSolved: Int,
    val acceptanceRate: Double,
    val ranking: Int?,
    val reputation: Int,
    val contributionPoints: Int,
    val badges: List<LeetcodeBadgeDto> = emptyList(),
    val recentSubmissions: List<LeetcodeSubmissionDto> = emptyList(),
    val lastUpdated: Long,
)