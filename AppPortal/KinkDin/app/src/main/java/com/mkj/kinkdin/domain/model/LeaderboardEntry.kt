package com.mkj.kinkdin.domain.model

data class LeaderboardEntry(
    val rank: Int,
    val user: User,
    val points: Int,
    val questionsolved: QuestionsSolved,
    val weeklyProgress: Int
)