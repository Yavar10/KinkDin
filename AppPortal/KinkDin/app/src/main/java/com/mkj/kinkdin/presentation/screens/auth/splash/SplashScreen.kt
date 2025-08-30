package com.mkj.kinkdin.presentation.screens.auth.splash


import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.EaseInOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun SplashScreen1(
    uiState: SplashUiState,
    onNavigateToLogin: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
) {
    // Auto-navigate after checking auth status
    LaunchedEffect(uiState.isCheckingAuth) {
        if (!uiState.isCheckingAuth) {
            delay(1000) // Show splash for 1 second
            if (uiState.isLoggedIn) {
                onNavigateToLeaderboard()
            } else {
                onNavigateToLogin()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App logo/name
            Text(
                text = "KinkeDin",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Leetcode Leaderboard",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Loading indicator
            CircularProgressIndicator(
                color = Color(0xFF6625D5),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


@Composable
fun SplashScreen(
    uiState: SplashUiState,
    onNavigateToLogin: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
) {
    // Animations
    val infiniteTransition = rememberInfiniteTransition()

    // Logo scale animation
    val logoScale by animateFloatAsState(
        targetValue = if (uiState.isCheckingAuth) 0.8f else 1f,
        animationSpec = tween(
            durationMillis = 1000,
            easing = EaseInOutCubic
        )
    )

    // Logo alpha animation
    val logoAlpha by animateFloatAsState(
        targetValue = if (uiState.isCheckingAuth) 0.7f else 1f,
        animationSpec = tween(
            durationMillis = 800,
            easing = EaseInOutCubic
        )
    )

    // Pulsing animation for the loading indicator
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Text fade in animation
    val textAlpha by animateFloatAsState(
        targetValue = if (uiState.isCheckingAuth) 0.6f else 1f,
        animationSpec = tween(
            durationMillis = 1000,
            delayMillis = 300,
            easing = EaseInOutCubic
        )
    )

    // Auto-navigate after checking auth status
    LaunchedEffect(uiState.isCheckingAuth, uiState.isLoggedIn) {
        if (!uiState.isCheckingAuth) {
            delay(1200) // Show splash a bit longer for better UX
            if (uiState.isLoggedIn) {
                onNavigateToLeaderboard()
            } else {
                onNavigateToLogin()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App Logo/Icon
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .scale(logoScale)
                    .alpha(logoAlpha)
                    .background(
                        color = Color(0xFF6625D5),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "K",
                    color = Color.White,
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // App Name
            Text(
                text = "KinkeDin",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.alpha(logoAlpha)
            )

            // Tagline
            Text(
                text = "Leetcode Leaderboard Competition",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .alpha(textAlpha)
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Loading Section
            if (uiState.isCheckingAuth) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Custom pulsing loading indicator
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .scale(pulseScale)
                            .background(
                                color = Color(0xFF6625D5).copy(alpha = 0.3f),
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = Color(0xFF6625D5),
                            strokeWidth = 3.dp,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = uiState.loadingMessage,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.alpha(textAlpha)
                    )
                }
            }

            // Error message if any
            if (uiState.error != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Red.copy(alpha = 0.1f)
                    ),
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Text(
                        text = uiState.error,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }
        }

        // Version info at the bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Version 1.0.0",
                color = Color.Gray.copy(alpha = 0.6f),
                fontSize = 10.sp
            )
            Text(
                text = "© 2025 KinkeDin",
                color = Color.Gray.copy(alpha = 0.4f),
                fontSize = 8.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}


@Composable
fun EnhancedSplashScreen(
    uiState: SplashUiState,
    onNavigateToLogin: () -> Unit,
    onNavigateToLeaderboard: () -> Unit,
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Rotating animation for background elements
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Pulsing animation
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.2f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        )
    )

    // Logo entrance animation
    val logoScale by animateFloatAsState(
        targetValue = 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    // Auto-navigate
    LaunchedEffect(uiState.isCheckingAuth, uiState.isLoggedIn) {
        if (!uiState.isCheckingAuth) {
            kotlinx.coroutines.delay(1500)
            if (uiState.isLoggedIn) {
                onNavigateToLeaderboard()
            } else {
                onNavigateToLogin()
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Animated background elements
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .rotate(rotation)
        ) {
            drawAnimatedBackground(this, pulse)
        }

        // Main content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Animated logo
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .scale(logoScale * pulse)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color(0xFF6625D5),
                                Color(0xFF9C27B0)
                            )
                        ),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "K",
                    color = Color.White,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // App name with gradient
            Text(
                text = "KinkeDin",
                fontSize = 42.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "Level up your coding game",
                fontSize = 16.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp)
            )

            Spacer(modifier = Modifier.height(64.dp))

            // Enhanced loading indicator
            if (uiState.isCheckingAuth) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Custom loading animation
                    Box(
                        modifier = Modifier.size(60.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        repeat(3) { index ->
                            val animatedAlpha by infiniteTransition.animateFloat(
                                initialValue = 0.1f,
                                targetValue = 1f,
                                animationSpec = infiniteRepeatable(
                                    animation = tween(
                                        durationMillis = 1000,
                                        delayMillis = index * 200
                                    ),
                                    repeatMode = RepeatMode.Reverse
                                )
                            )

                            Box(
                                modifier = Modifier
                                    .size((20 + index * 10).dp)
                                    .alpha(animatedAlpha)
                                    .background(
                                        Color(0xFF6625D5).copy(alpha = 0.3f),
                                        CircleShape
                                    )
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = uiState.loadingMessage,
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Bottom info
        Text(
            text = "Made with ❤️ for developers",
            color = Color.Gray.copy(alpha = 0.6f),
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        )
    }
}

fun drawAnimatedBackground(scope: DrawScope, pulse: Float) {
    val width = scope.size.width
    val height = scope.size.height
    val centerX = width / 2
    val centerY = height / 2

    // Draw multiple circles with different sizes and opacities
    repeat(5) { index ->
        val radius = (100 + index * 80) * pulse
        val alpha = (0.05f - index * 0.01f).coerceAtLeast(0.01f)

        scope.drawCircle(
            color = Color(0xFF6625D5).copy(alpha = alpha),
            radius = radius,
            center = Offset(centerX, centerY)
        )
    }

    // Draw some floating dots
    repeat(15) { index ->
        val angle = (index * 24f) * (kotlin.math.PI / 180f)
        val distance = 200f + (index % 3) * 50f
        val x = centerX + cos(angle).toFloat() * distance * pulse
        val y = centerY + sin(angle).toFloat() * distance * pulse

        scope.drawCircle(
            color = Color.White.copy(alpha = 0.1f),
            radius = (3 + index % 3).toFloat(),
            center = Offset(x, y)
        )
    }
}