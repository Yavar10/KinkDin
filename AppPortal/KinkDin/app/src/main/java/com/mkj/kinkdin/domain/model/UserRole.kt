package com.mkj.kinkdin.domain.model

enum class UserRole {
    USER,
    PREMIUM_USER,
    ADMIN;

    companion object {
        fun fromString(roleString: String?): UserRole {
            return when (roleString?.uppercase()) {
                "ADMIN" -> ADMIN
                "PREMIUM_USER", "PREMIUM" -> PREMIUM_USER
                else -> USER
            }
        }
    }

    fun toDisplayString(): String {
        return when (this) {
            USER -> "User"
            PREMIUM_USER -> "Premium User"
            ADMIN -> "Admin"
        }
    }
}
