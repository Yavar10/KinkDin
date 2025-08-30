package com.mkj.kinkdin.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

/**
 * Extension functions for cleaner navigation
 */

fun NavController.navigateToProfile(userId: String) {
    navigate(Screen.Profile.createRoute(userId))
}

fun NavController.navigateToLeaderboard(clearBackStack: Boolean = false) {
    navigate(Screen.Leaderboard.route) {
        if (clearBackStack) {
            popUpTo(0) { inclusive = true }
        }
    }
}

fun NavController.navigateToLogin(clearBackStack: Boolean = false) {
    navigate(Screen.Login.route) {
        if (clearBackStack) {
            popUpTo(0) { inclusive = true }
        }
    }
}

fun NavController.navigateToSettings() {
    navigate(Screen.Settings.route)
}

fun NavController.navigateToEditProfile() {
    navigate(Screen.EditProfile.route)
}

// Safe navigation - checks if destination exists before navigating
fun NavController.safeNavigate(route: String, navOptions: NavOptions? = null) {
    try {
        navigate(route, navOptions)
    } catch (e: IllegalArgumentException) {
        // Handle navigation error gracefully
        // Log error or show user feedback
    }
}

// Navigate and clear specific screen from back stack
fun NavController.navigateAndClearRoute(newRoute: String, routeToClear: String) {
    navigate(newRoute) {
        popUpTo(routeToClear) { inclusive = true }
    }
}
