package com.mkj.kinkdin.presentation.components.common.Profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkj.kinkdin.domain.model.User
import com.mkj.kinkdin.domain.model.UserRole

@Composable
fun ProfileContent(
    user: User,
    isCurrentUser: Boolean,
    onLeetcodeClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Profile Header
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Color.Black.copy(alpha = 0.4f)
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Profile Picture
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(Color(0xFF6625D5), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = user.username.take(2).uppercase(),
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Username and role
                Text(
                    text = user.username,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 4.dp)
                ) {
                    Text(
                        text = when (user.role) {
                            UserRole.ADMIN -> "Admin"
                            UserRole.PREMIUM_USER -> "Premium User"
                            UserRole.USER -> "User"
                        },
                        color = when (user.role) {
                            UserRole.ADMIN -> Color(0xFFFFD700)
                            UserRole.PREMIUM_USER -> Color(0xFF6625D5)
                            UserRole.USER -> Color.Gray
                        },
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )

                    if (user.role != UserRole.USER) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = when (user.role) {
                                UserRole.ADMIN -> Icons.Default.AdminPanelSettings
                                UserRole.PREMIUM_USER -> Icons.Default.Star
                                else -> Icons.Default.Person
                            },
                            contentDescription = null,
                            tint = when (user.role) {
                                UserRole.ADMIN -> Color(0xFFFFD700)
                                UserRole.PREMIUM_USER -> Color(0xFF6625D5)
                                else -> Color.Gray
                            },
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Leetcode profile button
                OutlinedButton(
                    onClick = onLeetcodeClick,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF6625D5)
                    ),
                    border = ButtonDefaults.outlinedButtonBorder.copy(
                        brush = BorderStroke(
                            1.dp,
                            Color(0xFF6625D5)
                        ).brush
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("@${user.leetcodeUsername}")
                }
            }
        }

        // Stats Cards
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                title = "Total Points",
                value = user.totalPoints.toString(),
                modifier = Modifier.weight(1f)
            )
            StatCard(
                title = "Weekly Points",
                value = user.weeklyPoints.toString(),
                modifier = Modifier.weight(1f)
            )
        }

        // Current rank if available
        user.currentRank?.let { rank ->
            StatCard(
                title = "Current Rank",
                value = "#$rank",
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Detailed Stats (Mock data for now)
        DetailedStatsCard()

        // Recent Activity (Mock data for now)
        RecentActivityCard()
    }
}

