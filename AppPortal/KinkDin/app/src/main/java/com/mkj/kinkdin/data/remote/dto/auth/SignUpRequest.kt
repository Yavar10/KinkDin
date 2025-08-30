package com.mkj.kinkdin.data.remote.dto.auth

data class SignUpRequest(
    val username: String,
    val email: String,
    val password: String,
    val leetcodeUsername: String,
    val fullName: String? = null
)