
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.mkj.kinkdin.presentation.navigation.NavigationArgs
import com.mkj.kinkdin.presentation.navigation.Screen
import com.mkj.kinkdin.presentation.screens.auth.login.LoginScreen
import com.mkj.kinkdin.presentation.screens.auth.login.LoginViewModel
import com.mkj.kinkdin.presentation.screens.auth.signup.SignUpScreen
import com.mkj.kinkdin.presentation.screens.auth.signup.SignUpViewModel
import com.mkj.kinkdin.presentation.screens.auth.splash.SplashScreen
import com.mkj.kinkdin.presentation.screens.auth.splash.SplashViewModel
import com.mkj.kinkdin.presentation.screens.leaderboard.LeaderboardScreen
import com.mkj.kinkdin.presentation.screens.leaderboard.LeaderboardViewModel
import com.mkj.kinkdin.presentation.screens.profile.ProfileScreen
import com.mkj.kinkdin.presentation.screens.profile.ProfileViewModel
import com.mkj.kinkdin.presentation.screens.profile.EditProfileScreen
import com.mkj.kinkdin.presentation.screens.profile.EditProfileViewModel
import com.mkj.kinkdin.presentation.screens.settings.SettingsScreen
import com.mkj.kinkdin.presentation.screens.settings.SettingsViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {

        // ==================== AUTH SCREENS ====================

        // Splash Screen - Check if user is logged in
        composable(Screen.Splash.route) {
            val viewModel: SplashViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            SplashScreen(
                uiState = uiState,
                onNavigateToLogin = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToLeaderboard = {
                    navController.navigate(Screen.Leaderboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Login Screen
        composable(Screen.Login.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            // Handle login success
            androidx.compose.runtime.LaunchedEffect(uiState.isLoginSuccessful) {
                if (uiState.isLoginSuccessful) {
                    navController.navigate(Screen.Leaderboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            }

            LoginScreen(
                uiState = uiState,
                onEmailChange = viewModel::updateEmail,
                onPasswordChange = viewModel::updatePassword,
                onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
                onLoginClick = viewModel::login,
                onNavigateToSignUp = {
                    navController.navigate(Screen.SignUp.route)
                },
                onBackClick = {
                    navController.popBackStack()
                },
                onForgotPasswordClick = {
                    // TODO: Implement forgot password flow
                }
            )
        }

        // SignUp Screen
        composable(Screen.SignUp.route) {
            val viewModel: SignUpViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            // Handle signup success
            androidx.compose.runtime.LaunchedEffect(uiState.isSignUpSuccessful) {
                if (uiState.isSignUpSuccessful) {
                    navController.navigate(Screen.Leaderboard.route) {
                        popUpTo(Screen.SignUp.route) { inclusive = true }
                    }
                }
            }

            SignUpScreen(
                uiState = uiState,
                onFullNameChange = viewModel::updateFullName,
                onUsernameChange = viewModel::updateUsername,
                onEmailChange = viewModel::updateEmail,
                onLeetcodeUsernameChange = viewModel::updateLeetcodeUsername,
                onPasswordChange = viewModel::updatePassword,
                onConfirmPasswordChange = viewModel::updateConfirmPassword,
                onTogglePasswordVisibility = viewModel::togglePasswordVisibility,
                onToggleConfirmPasswordVisibility = viewModel::toggleConfirmPasswordVisibility,
                onAgreeToTermsChange = viewModel::updateAgreeToTerms,
                onSignUpClick = viewModel::signUp,
                onNavigateToLogin = {
                    navController.popBackStack()
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // ==================== MAIN APP SCREENS ====================

        // Leaderboard Screen (Home/Main Screen)
        composable(Screen.Leaderboard.route) {
            val viewModel: LeaderboardViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            LeaderboardScreen(
                uiState = uiState,
                onRefresh = viewModel::refreshLeaderboard,
                onUserClick = { userId ->
                    navController.navigate(Screen.Profile.createRoute(userId))
                },
                onProfileClick = { userId ->
                    navController.navigate(Screen.Profile.createRoute(userId))
                },
                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                },
                onLeetcodeProfileClick = { username ->
                    viewModel.openExternalLeetcodeProfile(username)
                }
            )
        }

        // Profile Screen (View any user's profile)
        composable(
            route = Screen.Profile.route,
            arguments = listOf(
                navArgument(NavigationArgs.USER_ID) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString(NavigationArgs.USER_ID) ?: ""
            val viewModel: ProfileViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            ProfileScreen(
                userId = userId,
                uiState = uiState,
                onLoadProfile = { viewModel.loadUserProfile(userId) },
                onBackClick = {
                    navController.popBackStack()
                },
                onEditProfileClick = {
                    navController.navigate(Screen.EditProfile.route)
                },
                onLeetcodeProfileClick = { username ->
                    viewModel.openExternalLeetcodeProfile(username)
                },
                onRefreshStats = {
                    viewModel.refreshUserStats(userId)
                }
            )
        }

        // Edit Profile Screen (Only for current user)
        composable(Screen.EditProfile.route) {
            val viewModel: EditProfileViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            EditProfileScreen(
                uiState = uiState,
                onFullNameChange = viewModel::updateFullName,
                onUsernameChange = viewModel::updateUsername,
                onLeetcodeUsernameChange = viewModel::updateLeetcodeUsername,
                onProfilePictureChange = viewModel::updateProfilePicture,
                onSaveClick = viewModel::saveProfile,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveSuccess = {
                    navController.popBackStack()
                }
            )
        }

        // Settings Screen
        composable(Screen.Settings.route) {
            val viewModel: SettingsViewModel = hiltViewModel()
            val uiState by viewModel.uiState.collectAsState()

            SettingsScreen(
                uiState = uiState,
                onBackClick = {
                    navController.popBackStack()
                },
                onLogoutClick = {
                    viewModel.logout()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onThemeChange = viewModel::updateTheme,
                onNotificationToggle = viewModel::toggleNotifications,
                onDeleteAccountClick = viewModel::deleteAccount,
                onPrivacyPolicyClick = {
                    // TODO: Open privacy policy
                },
                onTermsOfServiceClick = {
                    // TODO: Open terms of service
                }
            )
        }
    }
}
