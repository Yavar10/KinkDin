package com.mkj.kinkdin.domain.repository

import com.mkj.kinkdin.domain.model.LeetcodeStats
import com.mkj.kinkdin.util.Resource

interface ILeetcodeRepository {
    suspend fun syncUserStats(username: String): Resource<LeetcodeStats>
    suspend fun getUserStats(username: String): Resource<LeetcodeStats>
    suspend fun refreshUserStats(username: String): Resource<LeetcodeStats>
}