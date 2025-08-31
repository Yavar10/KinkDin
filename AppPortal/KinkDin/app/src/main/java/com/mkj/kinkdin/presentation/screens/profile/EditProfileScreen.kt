package com.mkj.kinkdin.presentation.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mkj.kinkdin.presentation.components.common.CustomTextField
import com.mkj.kinkdin.presentation.components.common.CustomButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(
    uiState: EditProfileUiState,
    onFullNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onLeetcodeUsernameChange: (String) -> Unit,
    onProfilePictureChange: (String) -> Unit,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit,
    onSaveSuccess: () -> Unit
) {
    // Handle save success navigation
    LaunchedEffect(uiState.isSaveSuccessful) {
        if (uiState.isSaveSuccessful) {
            onSaveSuccess()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Top App Bar
        TopAppBar(
            title = {
                Text(
                    text = "Edit Profile",
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

        // Edit form
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.4f)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Form fields
                CustomTextField(
                    value = uiState.fullName,
                    onValueChange = onFullNameChange,
                    label = "Full Name",
                    placeholder = "Enter your full name",
                    error = uiState.fullNameError
                )

                CustomTextField(
                    value = uiState.username,
                    onValueChange = onUsernameChange,
                    label = "Username",
                    placeholder = "Enter your username",
                    error = uiState.usernameError
                )

                CustomTextField(
                    value = uiState.leetcodeUsername,
                    onValueChange = onLeetcodeUsernameChange,
                    label = "LeetCode Username",
                    placeholder = "Enter your LeetCode username",
                    error = uiState.leetcodeUsernameError
                )

                // Profile Picture URL (for now, later can add image picker)
                CustomTextField(
                    value = uiState.profilePictureUrl,
                    onValueChange = onProfilePictureChange,
                    label = "Profile Picture URL",
                    placeholder = "Enter image URL (optional)",
                    error = uiState.profilePictureError
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Save button
                CustomButton(
                    text = "Save Changes",
                    onClick = onSaveClick,
                    isLoading = uiState.isLoading
                )

                // Error message
                if (uiState.error != null) {
                    Text(
                        text = uiState.error,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
