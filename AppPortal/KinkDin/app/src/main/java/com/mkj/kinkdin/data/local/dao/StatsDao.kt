package com.mkj.kinkdin.data.local.dao

import androidx.room.*
import com.mkj.kinkdin.data.local.entities.StatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StatsDao {

    @Query("SELECT * FROM user_stats WHERE userId = :userId")
    suspend fun getUserStats(userId: String): StatsEntity?

    @Query("SELECT * FROM user_stats WHERE userId = :userId")
    fun getUserStatsFlow(userId: String): Flow<StatsEntity?>

    @Query("SELECT * FROM user_stats")
    suspend fun getAllStats(): List<StatsEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserStats(stats: StatsEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllStats(statsList: List<StatsEntity>)

    @Update
    suspend fun updateUserStats(stats: StatsEntity)

    @Query("DELETE FROM user_stats WHERE userId = :userId")
    suspend fun deleteUserStats(userId: String)

    @Query("DELETE FROM user_stats")
    suspend fun clearAllStats()

    @Query("SELECT * FROM user_stats WHERE lastSynced < :threshold")
    suspend fun getStaleStats(threshold: Long): List<StatsEntity>
}
