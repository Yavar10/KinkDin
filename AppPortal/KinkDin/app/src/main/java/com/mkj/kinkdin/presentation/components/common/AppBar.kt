package com.mkj.kinkdin.presentation.components.common

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
fun AppBar() {
    TopAppBar(
        title = { Text("") }
    )
}