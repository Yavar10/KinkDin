package com.mkj.kinkdin.data.remote.dto.auth

data class LoginRequest(
    val email: String,
    val password: String,
)
