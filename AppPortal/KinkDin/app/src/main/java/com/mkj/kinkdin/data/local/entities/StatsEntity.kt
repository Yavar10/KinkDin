package com.mkj.kinkdin.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class StatsEntity(
    @PrimaryKey
    val userId: String,
    val totalSolved: Int,
    val easySolved: Int,
    val mediumSolved: Int,
    val hardSolved: Int,
    val acceptanceRate: Double,
    val ranking: Int?,
    val reputation: Int,
    val contributionPoints: Int,
    val recentActivity: String, // JSON string
    val lastSynced: Long = System.currentTimeMillis()
)
