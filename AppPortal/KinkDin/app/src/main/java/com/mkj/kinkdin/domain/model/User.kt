package com.mkj.kinkdin.domain.model


data class User(
    val id: String,
    val username: String,
    val email: String,
    val fullName: String? = null,
    val leetcodeUsername: String,
    val role: UserRole,
    val profilePicture: String? = null,
    val joinedDate: Long,
    val totalPoints: Int = 0,
    val weeklyPoints: Int = 0,
    val currentRank: Int? = null,
    val isActive: Boolean = true,
    val lastSeen: Long = System.currentTimeMillis(),
    val leetcodeStats: LeetcodeStats? = null
) {
    companion object {
        fun empty() = User(
            id = "",
            username = "",
            email = "",
            fullName = null,
            leetcodeUsername = "",
            role = UserRole.USER,
            profilePicture = null,
            joinedDate = System.currentTimeMillis(),
            totalPoints = 0,
            weeklyPoints = 0,
            currentRank = null,
            isActive = true,
            lastSeen = System.currentTimeMillis(),
            leetcodeStats = null
        )

        fun create(
            id: String,
            username: String,
            email: String,
            leetcodeUsername: String,
            fullName: String? = null,
            role: UserRole = UserRole.USER
        ) = User(
            id = id,
            username = username,
            email = email,
            fullName = fullName,
            leetcodeUsername = leetcodeUsername,
            role = role,
            profilePicture = null,
            joinedDate = System.currentTimeMillis(),
            totalPoints = 0,
            weeklyPoints = 0,
            currentRank = null,
            isActive = true,
            lastSeen = System.currentTimeMillis(),
            leetcodeStats = null
        )
    }

    fun getDisplayName(): String {
        return fullName?.takeIf { it.isNotBlank() } ?: username
    }

    fun getRoleDisplayName(): String {
        return role.toDisplayString()
    }

    fun getInitials(): String {
        return if (fullName?.isNotBlank() == true) {
            fullName.split(" ").take(2).map { it.first().uppercaseChar() }.joinToString("")
        } else {
            username.take(2).uppercase()
        }
    }

    fun isOnline(): Boolean {
        val fiveMinutesAgo = System.currentTimeMillis() - (5 * 60 * 1000)
        return isActive && lastSeen > fiveMinutesAgo
    }
}