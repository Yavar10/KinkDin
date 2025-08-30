package com.mkj.kinkdin.domain.usecase.auth

import com.mkj.kinkdin.domain.repository.IAuthRepository
import com.mkj.kinkdin.util.Resource
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(
        username: String,
        email: String,
        password: String,
        leetcodeUsername: String
    ): Resource<Boolean> {
        return try {
            authRepository.signUp(username, email, password, leetcodeUsername)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unexpected error occurred")
        }
    }
}