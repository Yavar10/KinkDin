package com.mkj.kinkdin.util


object Constants {
    // API Configuration
    const val BASE_URL = "https://your-api-domain.com/api/v1/"
    const val LEETCODE_BASE_URL = "https://leetcode.com/"

    // SharedPreferences Keys
    const val PREF_AUTH_TOKEN = "auth_token"
    const val PREF_USER_ID = "user_id"
    const val PREF_USER_ROLE = "user_role"

    // Database Configuration
    const val DATABASE_NAME = "kinkdin_database"
    const val DATABASE_VERSION = 1

    // Points System Configuration
    const val POINTS_EASY = 10
    const val POINTS_MEDIUM = 25
    const val POINTS_HARD = 50

    // Acceptance Rate Multipliers
    const val MULTIPLIER_VERY_HARD = 2.0 // < 30% acceptance
    const val MULTIPLIER_HARD = 1.5      // < 50% acceptance
    const val MULTIPLIER_MEDIUM = 1.2    // < 70% acceptance
    const val MULTIPLIER_EASY = 1.0      // >= 70% acceptance

    // Network Configuration
    const val NETWORK_TIMEOUT = 30L
    const val CONNECT_TIMEOUT = 30L
    const val READ_TIMEOUT = 30L
    const val WRITE_TIMEOUT = 30L

    // Date Formats
    const val DATE_FORMAT_DISPLAY = "MMM dd, yyyy"
    const val DATE_FORMAT_API = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val DATE_FORMAT_SHORT = "MM/dd/yyyy"

    // App Configuration
    const val APP_VERSION = "1.0.0"
    const val PRIVACY_POLICY_URL = "https://kinkdin.com/privacy"
    const val TERMS_OF_SERVICE_URL = "https://kinkdin.com/terms"

    // Pagination
    const val DEFAULT_PAGE_SIZE = 20
    const val MAX_PAGE_SIZE = 100

    // Cache Duration (in milliseconds)
    const val CACHE_DURATION_SHORT = 5 * 60 * 1000L      // 5 minutes
    const val CACHE_DURATION_MEDIUM = 30 * 60 * 1000L    // 30 minutes
    const val CACHE_DURATION_LONG = 2 * 60 * 60 * 1000L  // 2 hours

    // File Upload
    const val MAX_FILE_SIZE = 5 * 1024 * 1024 // 5MB
    val ALLOWED_IMAGE_TYPES = arrayOf("image/jpeg", "image/png", "image/jpg")

    // Notification Channels
    const val NOTIFICATION_CHANNEL_GENERAL = "general"
    const val NOTIFICATION_CHANNEL_RANKING = "ranking_updates"

    // Intent Extra Keys
    const val EXTRA_USER_ID = "extra_user_id"
    const val EXTRA_USERNAME = "extra_username"
    const val EXTRA_LEETCODE_USERNAME = "extra_leetcode_username"
}
