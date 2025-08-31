package com.mkj.kinkdin.domain.repository

import com.mkj.kinkdin.domain.model.Leaderboard
import com.mkj.kinkdin.domain.model.LeaderboardEntry
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.util.Resource
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUserProfile(userId: String): Resource<User>
    suspend fun updateUserProfile(user: User): Resource<User>
    fun getUserProfileFlow(userId: String): Flow<Resource<User>>
}