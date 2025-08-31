package com.mkj.kinkdin.util

import android.util.Patterns

object ValidationUtils {

    data class ValidationResult(
        val isValid: Boolean,
        val errorMessage: String? = null
    )

    // Email Validation
    fun validateEmail(email: String): ValidationResult {
        return when {
            email.isBlank() -> ValidationResult(false, "Email is required")
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() ->
                ValidationResult(false, "Please enter a valid email address")
            email.length > 254 ->
                ValidationResult(false, "Email is too long")
            else -> ValidationResult(true)
        }
    }

    // Password Validation
    fun validatePassword(password: String): ValidationResult {
        return when {
            password.isBlank() -> ValidationResult(false, "Password is required")
            password.length < 6 -> ValidationResult(false, "Password must be at least 6 characters")
            password.length > 128 -> ValidationResult(false, "Password is too long")
            !password.any { it.isDigit() } -> ValidationResult(false, "Password must contain at least one number")
            !password.any { it.isLetter() } -> ValidationResult(false, "Password must contain at least one letter")
            else -> ValidationResult(true)
        }
    }

    // Username Validation
    fun validateUsername(username: String): ValidationResult {
        return when {
            username.isBlank() -> ValidationResult(false, "Username is required")
            username.length < 3 -> ValidationResult(false, "Username must be at least 3 characters")
            username.length > 20 -> ValidationResult(false, "Username must be less than 20 characters")
            !username.matches(Regex("^[a-zA-Z0-9_]+$")) ->
                ValidationResult(false, "Username can only contain letters, numbers, and underscores")
            username.startsWith("_") || username.endsWith("_") ->
                ValidationResult(false, "Username cannot start or end with underscore")
            else -> ValidationResult(true)
        }
    }

    // Full Name Validation
    fun validateFullName(fullName: String): ValidationResult {
        return when {
            fullName.isBlank() -> ValidationResult(false, "Full name is required")
            fullName.length < 2 -> ValidationResult(false, "Full name must be at least 2 characters")
            fullName.length > 50 -> ValidationResult(false, "Full name must be less than 50 characters")
            !fullName.matches(Regex("^[a-zA-Z\\s]+$")) ->
                ValidationResult(false, "Full name can only contain letters and spaces")
            else -> ValidationResult(true)
        }
    }

    // LeetCode Username Validation
    fun validateLeetcodeUsername(username: String): ValidationResult {
        return when {
            username.isBlank() -> ValidationResult(false, "LeetCode username is required")
            username.length < 3 -> ValidationResult(false, "LeetCode username must be at least 3 characters")
            username.length > 20 -> ValidationResult(false, "LeetCode username must be less than 20 characters")
            !username.matches(Regex("^[a-zA-Z0-9_-]+$")) ->
                ValidationResult(false, "Invalid LeetCode username format")
            else -> ValidationResult(true)
        }
    }

    // Password Confirmation Validation
    fun validatePasswordConfirmation(password: String, confirmPassword: String): ValidationResult {
        return when {
            confirmPassword.isBlank() -> ValidationResult(false, "Please confirm your password")
            password != confirmPassword -> ValidationResult(false, "Passwords do not match")
            else -> ValidationResult(true)
        }
    }

    // URL Validation
    fun validateUrl(url: String): ValidationResult {
        return when {
            url.isBlank() -> ValidationResult(true) // URL is optional
            !Patterns.WEB_URL.matcher(url).matches() ->
                ValidationResult(false, "Please enter a valid URL")
            else -> ValidationResult(true)
        }
    }

    // Phone Number Validation
    fun validatePhoneNumber(phoneNumber: String): ValidationResult {
        return when {
            phoneNumber.isBlank() -> ValidationResult(true) // Phone is optional
            !Patterns.PHONE.matcher(phoneNumber).matches() ->
                ValidationResult(false, "Please enter a valid phone number")
            else -> ValidationResult(true)
        }
    }
}
