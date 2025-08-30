package com.mkj.kinkdin.presentation.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.kinkdin.presentation.components.common.Setting.SettingsItem
import com.mkj.kinkdin.presentation.components.common.Setting.SettingsSection
import com.mkj.kinkdin.presentation.components.common.Setting.SettingsToggleItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    uiState: SettingsUiState,
    onBackClick: () -> Unit,
    onLogoutClick: () -> Unit,
    onThemeChange: (String) -> Unit,
    onNotificationToggle: (Boolean) -> Unit,
    onDeleteAccountClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onTermsOfServiceClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Settings",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        // Settings content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Account Section
            SettingsSection(title = "Account") {
                SettingsItem(
                    icon = Icons.Default.Person,
                    title = "Profile",
                    subtitle = "Edit your profile information",
                    onClick = { /* Navigate to edit profile */ }
                )

                SettingsItem(
                    icon = Icons.Default.Security,
                    title = "Change Password",
                    subtitle = "Update your password",
                    onClick = { /* Handle password change */ }
                )
            }

            // Preferences Section
            SettingsSection(title = "Preferences") {
                SettingsToggleItem(
                    icon = Icons.Default.Notifications,
                    title = "Notifications",
                    subtitle = "Receive ranking updates",
                    isChecked = uiState.notificationsEnabled,
                    onToggle = onNotificationToggle
                )

                SettingsItem(
                    icon = Icons.Default.Palette,
                    title = "Theme",
                    subtitle = uiState.selectedTheme,
                    onClick = { /* Show theme picker */ }
                )
            }

            // About Section
            SettingsSection(title = "About") {
                SettingsItem(
                    icon = Icons.Default.Info,
                    title = "Privacy Policy",
                    subtitle = "Read our privacy policy",
                    onClick = onPrivacyPolicyClick
                )

                SettingsItem(
                    icon = Icons.Default.Description,
                    title = "Terms of Service",
                    subtitle = "Read our terms of service",
                    onClick = onTermsOfServiceClick
                )

                SettingsItem(
                    icon = Icons.Default.Help,
                    title = "Help & Support",
                    subtitle = "Get help or contact us",
                    onClick = { /* Handle help */ }
                )
            }

            // Danger Zone
            SettingsSection(title = "Danger Zone") {
                SettingsItem(
                    icon = Icons.Default.Logout,
                    title = "Sign Out",
                    subtitle = "Sign out of your account",
                    onClick = onLogoutClick,
                    textColor = Color(0xFFFF6B6B)
                )

                SettingsItem(
                    icon = Icons.Default.DeleteForever,
                    title = "Delete Account",
                    subtitle = "Permanently delete your account",
                    onClick = onDeleteAccountClick,
                    textColor = Color(0xFFFF4444)
                )
            }

            // App version
            Text(
                text = "Version 1.0.0",
                color = Color.Gray,
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}


