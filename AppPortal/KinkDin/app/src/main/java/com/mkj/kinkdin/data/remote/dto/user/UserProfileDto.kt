package com.mkj.kinkdin.data.remote.dto.user

data class UserProfileDto(
    val id: String,
    val username: String,
    val email: String,
    val fullName: String?,
    val leetcodeUsername: String,
    val role: String,
    val profilePicture: String?,
    val joinedDate: Long,
    val totalPoints: Int,
    val weeklyPoints: Int,
    val currentRank: Int?,
    val isActive: Boolean = true,
    val lastSeen: Long = System.currentTimeMillis()
)