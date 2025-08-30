package com.mkj.kinkdin.data.local.dao


import androidx.room.*
import com.mkj.kinkdin.data.local.entities.LeaderboardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaderboardDao {

    @Query("SELECT * FROM leaderboard ORDER BY rank ASC")
    suspend fun getLeaderboard(): List<LeaderboardEntity>

    @Query("SELECT * FROM leaderboard ORDER BY rank ASC")
    fun getLeaderboardFlow(): Flow<List<LeaderboardEntity>>

    @Query("SELECT * FROM leaderboard WHERE weekRange = :weekRange ORDER BY rank ASC")
    suspend fun getLeaderboardForWeek(weekRange: String): List<LeaderboardEntity>

    @Query("SELECT * FROM leaderboard WHERE userId = :userId")
    suspend fun getUserLeaderboardEntry(userId: String): LeaderboardEntity?

    @Query("SELECT rank FROM leaderboard WHERE userId = :userId")
    suspend fun getUserRank(userId: String): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeaderboardEntry(entry: LeaderboardEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLeaderboard(entries: List<LeaderboardEntity>)

    @Query("DELETE FROM leaderboard WHERE weekRange != :currentWeek")
    suspend fun deleteOldLeaderboardEntries(currentWeek: String)

    @Query("DELETE FROM leaderboard")
    suspend fun clearLeaderboard()
}
