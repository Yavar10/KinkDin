package com.mkj.kinkdin.presentation.screens.auth.signup


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.kinkdin.R
import com.mkj.kinkdin.presentation.components.common.CustomButton
import com.mkj.kinkdin.presentation.components.common.CustomTextField
import com.mkj.kinkdin.presentation.components.common.MovingGradientBackground

@Composable
fun SignUpScreen(
    uiState: SignUpUiState,
    onFullNameChange: (String) -> Unit,
    onUsernameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onLeetcodeUsernameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onTogglePasswordVisibility: () -> Unit,
    onToggleConfirmPasswordVisibility: () -> Unit,
    onAgreeToTermsChange: (Boolean) -> Unit,
    onSignUpClick: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Moving gradient background
        MovingGradientBackground()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Back button
            IconButton(
                onClick = onBackClick,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Main content
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color.Black.copy(alpha = 0.4f)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Code icon
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .background(
                                Color(0xFF6625D5),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Join " + stringResource(R.string.app_name),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6625D5)
                    )

                    Text(
                        text = "Create your account and start competing",
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Full Name field
                    CustomTextField(
                        value = uiState.fullName,
                        onValueChange = onFullNameChange,
                        label = "Full Name",
                        placeholder = "John Doe",
                        error = uiState.fullNameError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Username field
                    CustomTextField(
                        value = uiState.username,
                        onValueChange = onUsernameChange,
                        label = "Username",
                        placeholder = "johndoe",
                        error = uiState.usernameError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email field
                    CustomTextField(
                        value = uiState.email,
                        onValueChange = onEmailChange,
                        label = "Email",
                        placeholder = "john@example.com",
                        error = uiState.emailError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // LeetCode Username field
                    CustomTextField(
                        value = uiState.leetcodeUsername,
                        onValueChange = onLeetcodeUsernameChange,
                        label = "LeetCode Username",
                        placeholder = "Your LeetCode username",
                        error = uiState.leetcodeUsernameError
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Password field
                    CustomTextField(
                        value = uiState.password,
                        onValueChange = onPasswordChange,
                        label = "Password",
                        placeholder = "Create a strong password",
                        error = uiState.passwordError,
                        isPassword = true,
                        isPasswordVisible = uiState.isPasswordVisible,
                        onPasswordToggle = onTogglePasswordVisibility
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Confirm Password field
                    CustomTextField(
                        value = uiState.confirmPassword,
                        onValueChange = onConfirmPasswordChange,
                        label = "Confirm Password",
                        placeholder = "Confirm your password",
                        error = uiState.confirmPasswordError,
                        isPassword = true,
                        isPasswordVisible = uiState.isConfirmPasswordVisible,
                        onPasswordToggle = onToggleConfirmPasswordVisibility
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Terms and conditions checkbox
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = uiState.agreeToTerms,
                            onCheckedChange = onAgreeToTermsChange,
                            colors = CheckboxDefaults.colors(
                                checkedColor = Color(0xFF6625D5)
                            )
                        )

                        Text(
                            text = "I agree to the ",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "Terms of Service",
                            color = Color(0xFF6625D5),
                            fontSize = 12.sp,
                            modifier = Modifier.clickable { /* TODO: Open terms */ }
                        )

                        Text(
                            text = " and ",
                            color = Color.Gray,
                            fontSize = 12.sp
                        )

                        Text(
                            text = "Privacy Policy",
                            color = Color(0xFF6625D5),
                            fontSize = 12.sp,
                            modifier = Modifier.clickable { /* TODO: Open privacy policy */ }
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Create Account button
                    CustomButton(
                        text = "Create Account",
                        onClick = onSignUpClick,
                        isLoading = uiState.isLoading
                    )

                    // Error message
                    if (uiState.error != null) {
                        Text(
                            text = uiState.error,
                            color = Color.Red,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Navigate to login
                    Row {
                        Text(
                            text = "Already have an account? ",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Sign in",
                            color = Color(0xFF6625D5),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.clickable { onNavigateToLogin() }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}




