package com.mkj.kinkdin.presentation.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

// Custom shapes for the app
val CardShape = RoundedCornerShape(12.dp)
val ButtonShape = RoundedCornerShape(8.dp)
val TextFieldShape = RoundedCornerShape(8.dp)
val BottomSheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)

