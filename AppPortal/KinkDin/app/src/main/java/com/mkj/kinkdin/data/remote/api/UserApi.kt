package com.mkj.kinkdin.data.remote.api

import com.mkj.kinkdin.data.remote.dto.auth.UserDto
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @GET("users/{userId}")
    suspend fun getUserProfile(@Path("userId") userId: String): Response<UserDto>

    @PUT("users/{userId}")
    suspend fun updateUserProfile(
        @Path("userId") userId: String,
        @Body userDto: UserDto
    ): Response<UserDto>

    @GET("users/me")
    suspend fun getCurrentUser(): Response<UserDto>

    @DELETE("users/{userId}")
    suspend fun deleteUser(@Path("userId") userId: String): Response<Unit>

    @GET("users")
    suspend fun getAllUsers(
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<List<UserDto>>
}
