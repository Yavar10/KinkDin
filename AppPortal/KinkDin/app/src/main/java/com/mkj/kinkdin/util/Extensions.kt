package com.mkj.kinkdin.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

// String Extensions
fun String.isValidEmail(): Boolean {
    return ValidationUtils.validateEmail(this).isValid
}

fun String.capitalizeWords(): String {
    return split(" ").joinToString(" ") { word ->
        word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
    }
}

fun String.truncate(maxLength: Int, ellipsis: String = "..."): String {
    return if (length <= maxLength) this else take(maxLength - ellipsis.length) + ellipsis
}

// Int Extensions
fun Int.formatWithCommas(): String {
    return String.format("%,d", this)
}

// Long Extensions (for timestamps)
fun Long.toRelativeTime(): String {
    return DateUtils.getRelativeTime(this)
}

fun Long.toDisplayDate(): String {
    return DateUtils.formatToDisplayDate(this)
}

fun Long.isToday(): Boolean {
    return DateUtils.isToday(this)
}

fun Long.isThisWeek(): Boolean {
    return DateUtils.isThisWeek(this)
}

// Collection Extensions
fun <T> List<T>.safe(index: Int): T? {
    return if (index >= 0 && index < size) this[index] else null
}

// Compose Extensions
@Composable
fun <T> Flow<T>.collectAsEffect(block: (T) -> Unit) {
    LaunchedEffect(this) {
        collect(block)
    }
}

// Context Extensions (for use in ViewModels with @ApplicationContext)
fun android.content.Context.showToast(message: String) {
    android.widget.Toast.makeText(this, message, android.widget.Toast.LENGTH_SHORT).show()
}
