package com.mkj.kinkdin.util
import kotlin.math.roundToInt

object PointsCalculator {

    enum class QuestionDifficulty {
        EASY, MEDIUM, HARD
    }

    /**
     * Calculate points for a single question based on difficulty and acceptance rate
     */
    fun calculateQuestionPoints(
        difficulty: QuestionDifficulty,
        acceptanceRate: Double
    ): Int {
        val basePoints = when (difficulty) {
            QuestionDifficulty.EASY -> Constants.POINTS_EASY
            QuestionDifficulty.MEDIUM -> Constants.POINTS_MEDIUM
            QuestionDifficulty.HARD -> Constants.POINTS_HARD
        }

        val acceptanceRateMultiplier = when {
            acceptanceRate < 30 -> Constants.MULTIPLIER_VERY_HARD
            acceptanceRate < 50 -> Constants.MULTIPLIER_HARD
            acceptanceRate < 70 -> Constants.MULTIPLIER_MEDIUM
            else -> Constants.MULTIPLIER_EASY
        }

        return (basePoints * acceptanceRateMultiplier).roundToInt()
    }

    /**
     * Calculate total points for a list of questions
     */
    fun calculateTotalPoints(questions: List<LeetcodeQuestion>): Int {
        return questions.sumOf { question ->
            calculateQuestionPoints(question.difficulty, question.acceptanceRate)
        }
    }

    /**
     * Calculate weekly points from solved questions
     */
    fun calculateWeeklyPoints(
        solvedQuestions: List<LeetcodeQuestion>,
        weekStartTimestamp: Long = getWeekStartTimestamp()
    ): Int {
        val weeklyQuestions = solvedQuestions.filter { question ->
            question.solvedAt >= weekStartTimestamp
        }
        return calculateTotalPoints(weeklyQuestions)
    }

    /**
     * Calculate points breakdown by difficulty
     */
    fun calculatePointsBreakdown(questions: List<LeetcodeQuestion>): PointsBreakdown {
        var easyPoints = 0
        var mediumPoints = 0
        var hardPoints = 0

        questions.forEach { question ->
            val points = calculateQuestionPoints(question.difficulty, question.acceptanceRate)
            when (question.difficulty) {
                QuestionDifficulty.EASY -> easyPoints += points
                QuestionDifficulty.MEDIUM -> mediumPoints += points
                QuestionDifficulty.HARD -> hardPoints += points
            }
        }

        return PointsBreakdown(
            easyPoints = easyPoints,
            mediumPoints = mediumPoints,
            hardPoints = hardPoints,
            totalPoints = easyPoints + mediumPoints + hardPoints
        )
    }

    /**
     * Get the start of current week (Monday 00:00:00)
     */
    private fun getWeekStartTimestamp(): Long {
        val calendar = java.util.Calendar.getInstance()
        calendar.firstDayOfWeek = java.util.Calendar.MONDAY
        calendar.set(java.util.Calendar.DAY_OF_WEEK, java.util.Calendar.MONDAY)
        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0)
        calendar.set(java.util.Calendar.MINUTE, 0)
        calendar.set(java.util.Calendar.SECOND, 0)
        calendar.set(java.util.Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
}

data class PointsBreakdown(
    val easyPoints: Int,
    val mediumPoints: Int,
    val hardPoints: Int,
    val totalPoints: Int
)

data class LeetcodeQuestion(
    val id: String,
    val title: String,
    val difficulty: PointsCalculator.QuestionDifficulty,
    val acceptanceRate: Double,
    val solvedAt: Long,
    val timeTaken: Int? = null // in minutes
)
