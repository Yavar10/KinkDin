package com.mkj.kinkdin.presentation.components.common.Profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecentActivityCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.Black.copy(alpha = 0.3f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Recent Activity",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Mock activity items
            ActivityItem(
                title = "Solved: Two Sum",
                subtitle = "Easy • 2 hours ago",
                points = "+10"
            )

            ActivityItem(
                title = "Solved: Valid Parentheses",
                subtitle = "Easy • 4 hours ago",
                points = "+10"
            )

            ActivityItem(
                title = "Solved: Merge Two Sorted Lists",
                subtitle = "Easy • 6 hours ago",
                points = "+10"
            )
        }
    }
}
