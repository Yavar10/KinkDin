package com.mkj.kinkdin.domain.usecase.auth

import com.mkj.kinkdin.domain.repository.IAuthRepository
import com.mkj.kinkdin.util.Resource
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: IAuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Resource<Boolean> {
        return try {
            authRepository.login(email, password)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "An unexpected error occurred")
        }
    }
}