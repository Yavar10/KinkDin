package com.mkj.kinkdin.data.remote.api

import com.mkj.kinkdin.data.remote.dto.leetcode.LeetcodeStatsDto
import com.mkj.kinkdin.data.remote.dto.leetcode.LeetcodeProfileDto
import retrofit2.Response
import retrofit2.http.*

interface LeetcodeApi {

    @GET("leetcode/stats/{username}")
    suspend fun getUserStats(@Path("username") username: String): Response<LeetcodeStatsDto>

    @GET("leetcode/profile/{username}")
    suspend fun getUserProfile(@Path("username") username: String): Response<LeetcodeProfileDto>

    @POST("leetcode/sync/{username}")
    suspend fun syncUserStats(@Path("username") username: String): Response<LeetcodeStatsDto>

    @GET("leetcode/recent-submissions/{username}")
    suspend fun getRecentSubmissions(
        @Path("username") username: String,
        @Query("limit") limit: Int = 10
    ): Response<List<Any>> // Replace with proper DTO
}