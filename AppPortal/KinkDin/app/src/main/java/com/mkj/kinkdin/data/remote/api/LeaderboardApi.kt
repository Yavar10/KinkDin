package com.mkj.kinkdin.data.remote.api

import com.mkj.kinkdin.data.remote.dto.leaderboard.LeaderboardDto
import retrofit2.Response
import retrofit2.http.*

interface LeaderboardApi {

    @GET("leaderboard")
    suspend fun getLeaderboard(
        @Query("week") week: String? = null,
        @Query("limit") limit: Int = 50,
        @Query("offset") offset: Int = 0
    ): Response<LeaderboardDto>

    @GET("leaderboard/user-rank")
    suspend fun getUserRank(
        @Query("userId") userId: String,
        @Query("week") week: String? = null
    ): Response<Int>

    @POST("leaderboard/refresh")
    suspend fun refreshLeaderboard(): Response<LeaderboardDto>
}
