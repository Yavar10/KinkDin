package com.mkj.kinkdin.util

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("kinkdin_prefs", Context.MODE_PRIVATE)

    // Auth Token Management
    fun saveAuthToken(token: String) {
        sharedPreferences.edit()
            .putString(Constants.PREF_AUTH_TOKEN, token)
            .apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(Constants.PREF_AUTH_TOKEN, null)
    }

    // User ID Management
    fun saveUserId(userId: String) {
        sharedPreferences.edit()
            .putString(Constants.PREF_USER_ID, userId)
            .apply()
    }

    fun getUserId(): String? {
        return sharedPreferences.getString(Constants.PREF_USER_ID, null)
    }

    // User Role Management
    fun saveUserRole(role: String) {
        sharedPreferences.edit()
            .putString(Constants.PREF_USER_ROLE, role)
            .apply()
    }

    fun getUserRole(): String? {
        return sharedPreferences.getString(Constants.PREF_USER_ROLE, null)
    }

    // User Details
    fun saveUsername(username: String) {
        sharedPreferences.edit()
            .putString("username", username)
            .apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    fun saveEmail(email: String) {
        sharedPreferences.edit()
            .putString("email", email)
            .apply()
    }

    fun getEmail(): String? {
        return sharedPreferences.getString("email", null)
    }

    fun saveLeetcodeUsername(leetcodeUsername: String) {
        sharedPreferences.edit()
            .putString("leetcode_username", leetcodeUsername)
            .apply()
    }

    fun getLeetcodeUsername(): String? {
        return sharedPreferences.getString("leetcode_username", null)
    }

    // App Settings
    fun saveTheme(theme: String) {
        sharedPreferences.edit()
            .putString("app_theme", theme)
            .apply()
    }

    fun getTheme(): String {
        return sharedPreferences.getString("app_theme", "Dark") ?: "Dark"
    }

    fun saveNotificationsEnabled(enabled: Boolean) {
        sharedPreferences.edit()
            .putBoolean("notifications_enabled", enabled)
            .apply()
    }

    fun getNotificationsEnabled(): Boolean {
        return sharedPreferences.getBoolean("notifications_enabled", true)
    }

    // First Launch
    fun isFirstLaunch(): Boolean {
        return sharedPreferences.getBoolean("first_launch", true)
    }

    fun setFirstLaunchComplete() {
        sharedPreferences.edit()
            .putBoolean("first_launch", false)
            .apply()
    }

    // Login Status
    fun isLoggedIn(): Boolean {
        return !getAuthToken().isNullOrBlank() && !getUserId().isNullOrBlank()
    }

    // Clear all data (for logout)
    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

    // Clear only auth data (keep app preferences)
    fun clearAuthData() {
        sharedPreferences.edit()
            .remove(Constants.PREF_AUTH_TOKEN)
            .remove(Constants.PREF_USER_ID)
            .remove(Constants.PREF_USER_ROLE)
            .remove("username")
            .remove("email")
            .remove("leetcode_username")
            .apply()
    }
}