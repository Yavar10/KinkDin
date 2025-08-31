package com.mkj.kinkdin.data.remote.dto.auth

import com.mkj.kinkdin.data.remote.dto.auth.UserDto

data class SignUpResponse(
    val success: Boolean,
    val token: String,
    val user: UserDto,
    val expiresIn: Long,
    val message: String? = null
)