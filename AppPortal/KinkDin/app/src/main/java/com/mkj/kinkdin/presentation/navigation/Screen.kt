package com.mkj.kinkdin.presentation.navigation

sealed class Screen(val route: String) {
    // Auth screens
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignUp : Screen("signup")

    // Main app screens
    object Leaderboard : Screen("leaderboard")
    object Profile : Screen("profile/{userId}") {
        fun createRoute(userId: String) = "profile/$userId"
    }
    object EditProfile : Screen("edit_profile")
    object Settings : Screen("settings")

    // External navigation (for browser)
    object ExternalLeetcodeProfile : Screen("external_leetcode/{username}") {
        fun createRoute(username: String) = "external_leetcode/$username"
    }
}

// Navigation argument keys
object NavigationArgs {
    const val USER_ID = "userId"
    const val LEETCODE_USERNAME = "username"
}