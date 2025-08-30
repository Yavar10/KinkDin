package com.mkj.kinkdin.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "leaderboard")
data class LeaderboardEntity(
    @PrimaryKey
    val userId: String,
    val rank: Int,
    val points: Int,
    val easySolved: Int,
    val mediumSolved: Int,
    val hardSolved: Int,
    val totalSolved: Int,
    val weeklyProgress: Int,
    val weekRange: String,
    val lastUpdated: Long = System.currentTimeMillis()
)