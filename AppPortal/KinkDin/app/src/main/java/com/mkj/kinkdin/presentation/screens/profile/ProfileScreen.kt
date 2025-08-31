package com.mkj.kinkdin.presentation.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.kinkdin.presentation.components.common.Profile.ProfileContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    userId: String,
    uiState: ProfileUiState,
    onLoadProfile: () -> Unit,
    onBackClick: () -> Unit,
    onEditProfileClick: () -> Unit,
    onLeetcodeProfileClick: (String) -> Unit,
    onRefreshStats: () -> Unit
) {
    // Load profile when screen is first displayed
    LaunchedEffect(userId) {
        onLoadProfile()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = if (uiState.isCurrentUser) "My Profile" else "Profile",
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
            actions = {
                if (uiState.isCurrentUser) {
                    IconButton(onClick = onEditProfileClick) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Profile",
                            tint = Color.White
                        )
                    }
                }
                IconButton(onClick = onRefreshStats) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh Stats",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent
            )
        )

        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color(0xFF6625D5))
            }
        } else if (uiState.error != null) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "Error loading profile",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = uiState.error,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Button(
                        onClick = onLoadProfile,
                        modifier = Modifier.padding(top = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6625D5))
                    ) {
                        Text("Retry")
                    }
                }
            }
        } else if (uiState.user != null) {
            ProfileContent(
                user = uiState.user,
                isCurrentUser = uiState.isCurrentUser,
                onLeetcodeClick = { onLeetcodeProfileClick(uiState.user.leetcodeUsername) }
            )
        }
    }
}

