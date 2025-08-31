package com.mkj.kinkdin.domain.model

data class QuestionsSolved(
    val easy: Int,
    val medium: Int,
    val hard: Int,
    val total: Int
) {
    companion object {
        fun empty() = QuestionsSolved(0, 0, 0, 0)
    }
}
