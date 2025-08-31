package com.mkj.kinkdin.data.remote.dto.auth

data class UserDto(
    val id: String,
    val username: String,
    val email: String,
    val leetcodeUsername: String,
    val role: String,
    val profilePicture: String?,
    val joinedDate: Long,
    val totalPoints: Int,
    val weeklyPoints: Int,
    val currentRank: Int?,
    val fullName: String? = null
)