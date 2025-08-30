package com.mkj.kinkdin.data.repository

import com.mkj.kinkdin.data.local.dao.StatsDao
import com.mkj.kinkdin.data.remote.api.LeetcodeApi
import com.mkj.kinkdin.domain.model.LeetcodeStats
import com.mkj.kinkdin.domain.repository.ILeetcodeRepository
import com.mkj.kinkdin.util.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeetcodeRepository @Inject constructor(
    private val leetcodeApi: LeetcodeApi,
    private val statsDao: StatsDao
) : ILeetcodeRepository {

    override suspend fun syncUserStats(username: String): Resource<LeetcodeStats> {
        // Mock implementation
        return Resource.Success(
            LeetcodeStats(
                username = username,
                totalSolved = 150,
                easySolved = 80,
                mediumSolved = 50,
                hardSolved = 20,
                acceptanceRate = 75.5,
                ranking = 12000,
                reputation = 500,
                contributionPoints = 100
            )
        )
    }

    override suspend fun getUserStats(username: String): Resource<LeetcodeStats> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshUserStats(username: String): Resource<LeetcodeStats> {
        TODO("Not yet implemented")
    }
}