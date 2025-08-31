package com.mkj.kinkdin.data.remote.dto.leetcode

data class LeetcodeProfileDto(
    val username: String,
    val realName: String?,
    val avatar: String?,
    val ranking: Int?,
    val reputation: Int,
    val githubUrl: String?,
    val twitterUrl: String?,
    val linkedinUrl: String?,
    val website: String?,
)