package com.mkj.kinkdin.data.repository

import com.mkj.kinkdin.data.local.dao.LeaderboardDao
import com.mkj.kinkdin.data.remote.api.LeaderboardApi
import com.mkj.kinkdin.domain.model.Leaderboard
import com.mkj.kinkdin.domain.model.LeaderboardEntry
import com.mkj.kinkdin.domain.model.QuestionsSolved
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.domain.model.UserRole
import com.mkj.kinkdin.domain.repository.ILeaderboardRepository
import com.mkj.kinkdin.util.DateUtils
import com.mkj.kinkdin.util.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LeaderboardRepository @Inject constructor(
    private val leaderboardApi: LeaderboardApi,
    private val leaderboardDao: LeaderboardDao,
) : ILeaderboardRepository {

    override suspend fun getLeaderboard(weekRange: String?): Resource<Leaderboard> {
        return try {
            // Mock implementation - replace with real API call
            delay(1000)

            val mockLeaderboardEntries = getMockLeaderboardEntries()
            val currentWeekRange = weekRange ?: DateUtils.getCurrentWeekRange()

            val leaderboard = Leaderboard(
                entries = mockLeaderboardEntries,
                weekRange = currentWeekRange,
                lastUpdated = System.currentTimeMillis(),
                totalUsers = mockLeaderboardEntries.size
            )

            Resource.Success(leaderboard)
        } catch (e: Exception) {
            Resource.Error("Failed to load leaderboard: ${e.message}")
        }
    }

    override suspend fun getUserRank(userId: String): Resource<Int> {
        return try {
            // Mock implementation
            delay(500)

            val mockRank = when (userId) {
                "1" -> 1
                "2" -> 2
                "3" -> 3
                else -> (4..100).random()
            }

            Resource.Success(mockRank)
        } catch (e: Exception) {
            Resource.Error("Failed to get user rank: ${e.message}")
        }
    }

    override suspend fun refreshLeaderboard(): Resource<Leaderboard> {
        return getLeaderboard()
    }

    override fun getLeaderboardFlow(): Flow<Resource<Leaderboard>> = flow {
        emit(Resource.Loading())
        emit(getLeaderboard())
    }

    private fun getMockLeaderboardEntries(): List<LeaderboardEntry> {
        return listOf(
            LeaderboardEntry(
                rank = 1,
                user = User(
                    id = "1",
                    username = "mkj77",
                    email = "mkj77@example.com",
                    fullName = "Michael Johnson",
                    leetcodeUsername = "mkj77",
                    role = UserRole.PREMIUM_USER,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 2450,
                    weeklyPoints = 380,
                    currentRank = 1,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                ),
                points = 380,
                questionsolved = QuestionsSolved(15, 12, 5, 32),
                weeklyProgress = 15
            ),
            LeaderboardEntry(
                rank = 2,
                user = User(
                    id = "2",
                    username = "AlgoQueen",
                    email = "algoqueen@example.com",
                    fullName = "Alice Smith",
                    leetcodeUsername = "algoqueen",
                    role = UserRole.USER,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 2200,
                    weeklyPoints = 340,
                    currentRank = 2,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                ),
                points = 340,
                questionsolved = QuestionsSolved(12, 10, 4, 26),
                weeklyProgress = 12
            ),
            LeaderboardEntry(
                rank = 3,
                user = User(
                    id = "3",
                    username = "ByteWarrior",
                    email = "bytewarrior@example.com",
                    fullName = "Bob Wilson",
                    leetcodeUsername = "bytewarrior",
                    role = UserRole.USER,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 1980,
                    weeklyPoints = 310,
                    currentRank = 3,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                ),
                points = 310,
                questionsolved = QuestionsSolved(10, 8, 3, 21),
                weeklyProgress = 10
            ),
            LeaderboardEntry(
                rank = 4,
                user = User(
                    id = "4",
                    username = "DataStructGuru",
                    email = "dsguru@example.com",
                    fullName = "Sarah Davis",
                    leetcodeUsername = "datastructguru",
                    role = UserRole.ADMIN,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 1850,
                    weeklyPoints = 285,
                    currentRank = 4,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                ),
                points = 285,
                questionsolved = QuestionsSolved(8, 7, 2, 17),
                weeklyProgress = 8
            ),
            LeaderboardEntry(
                rank = 5,
                user = User(
                    id = "5",
                    username = "RecursionKing",
                    email = "recursion@example.com",
                    fullName = "David Lee",
                    leetcodeUsername = "recursionking",
                    role = UserRole.USER,
                    profilePicture = null,
                    joinedDate = System.currentTimeMillis(),
                    totalPoints = 1720,
                    weeklyPoints = 260,
                    currentRank = 5,
                    isActive = true,
                    lastSeen = System.currentTimeMillis(),
                    leetcodeStats = null
                ),
                points = 260,
                questionsolved = QuestionsSolved(7, 6, 2, 15),
                weeklyProgress = 7
            )
        )
    }
}
