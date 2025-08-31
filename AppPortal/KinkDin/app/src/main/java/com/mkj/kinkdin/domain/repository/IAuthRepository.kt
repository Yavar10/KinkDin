package com.mkj.kinkdin.domain.repository

import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.util.Resource

interface IAuthRepository {
    suspend fun login(email: String, password: String): Resource<Boolean>
    suspend fun signUp(username: String, email: String, password: String, leetcodeUsername: String): Resource<Boolean>
    suspend fun logout(): Resource<Boolean>
    suspend fun refreshToken(): Resource<String>
    suspend fun isLoggedIn(): Boolean
    suspend fun getCurrentUser(): Resource<User?>
}