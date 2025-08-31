package com.mkj.kinkdin.data.repository


import com.mkj.kinkdin.data.local.dao.UserDao
import com.mkj.kinkdin.data.remote.api.AuthApi
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.domain.model.UserRole
import com.mkj.kinkdin.domain.repository.IAuthRepository
import com.mkj.kinkdin.util.PreferencesManager
import com.mkj.kinkdin.util.Resource
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi,
    private val userDao: UserDao,
    private val preferencesManager: PreferencesManager,
) : IAuthRepository {

    override suspend fun login(email: String, password: String): Resource<Boolean> {
        return try {
            // Mock implementation - replace with real API call
            delay(1000) // Simulate network delay

            // Mock successful login
            val mockToken = "mock_jwt_token_${System.currentTimeMillis()}"
            val mockUserId = "user_${email.hashCode().toString().takeLast(6)}"

            preferencesManager.saveAuthToken(mockToken)
            preferencesManager.saveUserId(mockUserId)
            preferencesManager.saveEmail(email)

            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error("Login failed: ${e.message}")
        }
    }

    override suspend fun signUp(
        username: String,
        email: String,
        password: String,
        leetcodeUsername: String,
    ): Resource<Boolean> {
        return try {
            // Mock implementation
            delay(1500)

            val mockToken = "mock_jwt_token_${System.currentTimeMillis()}"
            val mockUserId = "user_${email.hashCode().toString().takeLast(6)}"

            preferencesManager.saveAuthToken(mockToken)
            preferencesManager.saveUserId(mockUserId)
            preferencesManager.saveUsername(username)
            preferencesManager.saveEmail(email)
            preferencesManager.saveLeetcodeUsername(leetcodeUsername)

            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error("Sign up failed: ${e.message}")
        }
    }

    override suspend fun logout(): Resource<Boolean> {
        return try {
            preferencesManager.clearAuthData()
            Resource.Success(true)
        } catch (e: Exception) {
            Resource.Error("Logout failed: ${e.message}")
        }
    }

    override suspend fun refreshToken(): Resource<String> {
        // Mock implementation
        return Resource.Success("mock_refreshed_token")
    }

    override suspend fun isLoggedIn(): Boolean {
        return preferencesManager.isLoggedIn()
    }

    override suspend fun getCurrentUser(): Resource<User?> {
        return try {
            val userId = preferencesManager.getUserId()
            val username = preferencesManager.getUsername()
            val email = preferencesManager.getEmail()
            val leetcodeUsername = preferencesManager.getLeetcodeUsername()

            if (userId != null && username != null && email != null && leetcodeUsername != null) {
                val user = User(
                    id = userId,
                    username = username,
                    email = email,
                    fullName = null, // Could be stored separately
                    leetcodeUsername = leetcodeUsername,
                    role = UserRole.USER, // Default role, could be fetched from API
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 0,
                    weeklyPoints = 0,
                    currentRank = null,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                )
                Resource.Success(user)
            } else {
                Resource.Success(null)
            }
        } catch (e: Exception) {
            Resource.Error("Failed to get current user: ${e.message}")
        }
    }
}
