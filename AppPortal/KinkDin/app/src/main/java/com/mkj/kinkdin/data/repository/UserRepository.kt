package com.mkj.kinkdin.data.repository

import com.mkj.kinkdin.data.local.dao.UserDao
import com.mkj.kinkdin.data.remote.api.UserApi
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.domain.model.UserRole
import com.mkj.kinkdin.domain.repository.IUserRepository
import com.mkj.kinkdin.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val userApi: UserApi,
    private val userDao: UserDao,
) : IUserRepository {

    override suspend fun getUserProfile(userId: String): Resource<User> {
        return try {
            // Mock implementation - replace with real API call
            val mockUser = when (userId) {
                "1" -> User(
                    id = "1",
                    username = "mkj77",
                    email = "mkj77@example.com",
                    fullName = "Michael Johnson",
                    leetcodeUsername = "mkj77",
                    role = UserRole.PREMIUM_USER,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis() - (30L * 24 * 60 * 60 * 1000),
                    totalPoints = 1500,
                    weeklyPoints = 250,
                    currentRank = 1,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                )

                "2" -> User(
                    id = "2",
                    username = "AlgoQueen",
                    email = "algoqueen@example.com",
                    fullName = "Alice Smith",
                    leetcodeUsername = "algoqueen",
                    role = UserRole.USER,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis() - (45L * 24 * 60 * 60 * 1000),
                    totalPoints = 1200,
                    weeklyPoints = 180,
                    currentRank = 2,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                )

                else -> User(
                    id = userId,
                    username = "TestUser$userId",
                    email = "user$userId@example.com",
                    fullName = "Test User $userId",
                    leetcodeUsername = "testuser$userId",
                    role = UserRole.USER,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis() - (60L * 24 * 60 * 60 * 1000),
                    totalPoints = 800,
                    weeklyPoints = 120,
                    currentRank = 10,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                )
            }
            Resource.Success(mockUser)
        } catch (e: Exception) {
            Resource.Error("Failed to get user profile: ${e.message}")
        }
    }

    override suspend fun updateUserProfile(user: User): Resource<User> {
        return try {
            // Mock implementation - in real app, make API call
            // val response = userApi.updateUserProfile(user.id, user.toDto())
            // if (response.isSuccessful) {
            //     val updatedUser = response.body()?.toDomainModel()
            //     if (updatedUser != null) {
            //         Resource.Success(updatedUser)
            //     } else {
            //         Resource.Error("Failed to parse updated user data")
            //     }
            // } else {
            //     Resource.Error("Failed to update profile: ${response.message()}")
            // }

            // For now, just return the same user
            Resource.Success(user)
        } catch (e: Exception) {
            Resource.Error("Failed to update user profile: ${e.message}")
        }
    }

    override fun getUserProfileFlow(userId: String): Flow<Resource<User>> = flow {
        emit(Resource.Loading())
        emit(getUserProfile(userId))
    }

    // Additional helper methods
    suspend fun getCurrentUser(): Resource<User?> {
        return try {
            // Mock implementation - get current user from preferences/cache
            Resource.Success(null)
        } catch (e: Exception) {
            Resource.Error("Failed to get current user: ${e.message}")
        }
    }
}


