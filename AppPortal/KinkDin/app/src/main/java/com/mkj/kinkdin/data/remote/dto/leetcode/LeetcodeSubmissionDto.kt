package com.mkj.kinkdin.data.remote.dto.leetcode

data class LeetcodeSubmissionDto(
    val problemTitle: String,
    val problemId: String,
    val difficulty: String,
    val status: String,
    val runtime: String?,
    val memory: String?,
    val timestamp: Long,
    val language: String
)
