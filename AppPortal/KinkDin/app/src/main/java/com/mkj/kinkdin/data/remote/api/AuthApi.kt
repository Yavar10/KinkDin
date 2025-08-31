package com.mkj.kinkdin.data.remote.api

import com.mkj.kinkdin.data.remote.dto.auth.LoginRequest
import com.mkj.kinkdin.data.remote.dto.auth.LoginResponse
import com.mkj.kinkdin.data.remote.dto.auth.SignUpRequest
import com.mkj.kinkdin.data.remote.dto.auth.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("auth/signup")
    suspend fun signUp(@Body request: SignUpRequest): Response<SignUpResponse>

    @POST("auth/logout")
    suspend fun logout(): Response<Unit>

    @POST("auth/refresh")
    suspend fun refreshToken(): Response<LoginResponse>

    @POST("auth/verify")
    suspend fun verifyToken(): Response<LoginResponse>
}