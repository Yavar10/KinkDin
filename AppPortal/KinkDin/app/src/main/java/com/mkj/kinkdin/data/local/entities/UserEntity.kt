package com.mkj.kinkdin.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mkj.kinkdin.domain.model.UserRole

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val username: String,
    val email: String,
    val leetcodeUsername: String,
    val role: UserRole,
    val profilePicture: String?,
    val joinedDate: Long,
    val totalPoints: Int,
    val weeklyPoints: Int,
    val currentRank: Int?,
    val lastUpdated: Long = System.currentTimeMillis()
)
