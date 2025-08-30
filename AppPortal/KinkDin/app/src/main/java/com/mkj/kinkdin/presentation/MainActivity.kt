package com.mkj.kinkdin.presentation

import NavigationGraph
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.mkj.kinkdin.presentation.components.common.MovingGradientBackground
import com.mkj.kinkdin.presentation.theme.KinkdinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KinkdinTheme {
                KinkDinApp()
            }
        }
    }

    // Function to open external URLs (like Leetcode profiles)
    fun openExternalUrl(url: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } catch (e: Exception) {
            // Handle error - maybe show a toast
        }
    }
}

@Composable
fun KinkDinApp() {
    Box(modifier = Modifier.fillMaxSize()) {
        // Global moving gradient background
        MovingGradientBackground()

        // Navigation content
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background.copy(alpha = 0f) // Transparent to show gradient
        ) {
            val navController = rememberNavController()
            NavigationGraph(navController = navController)
        }
    }
}