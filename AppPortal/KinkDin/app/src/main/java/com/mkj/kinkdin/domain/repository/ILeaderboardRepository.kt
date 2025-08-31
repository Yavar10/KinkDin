package com.mkj.kinkdin.domain.repository

import com.mkj.kinkdin.domain.model.Leaderboard
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.util.Resource
import kotlinx.coroutines.flow.Flow



interface ILeaderboardRepository {
    suspend fun getLeaderboard(weekRange: String? = null): Resource<Leaderboard>
    suspend fun getUserRank(userId: String): Resource<Int>
    suspend fun refreshLeaderboard(): Resource<Leaderboard>
    fun getLeaderboardFlow(): Flow<Resource<Leaderboard>>
}