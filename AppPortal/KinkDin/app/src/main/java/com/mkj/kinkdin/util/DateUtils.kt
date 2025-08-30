package com.mkj.kinkdin.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val displayDateFormat = SimpleDateFormat(Constants.DATE_FORMAT_DISPLAY, Locale.getDefault())
    private val apiDateFormat = SimpleDateFormat(Constants.DATE_FORMAT_API, Locale.getDefault())
    private val shortDateFormat = SimpleDateFormat(Constants.DATE_FORMAT_SHORT, Locale.getDefault())

    init {
        apiDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    }

    // Format timestamp to display date
    fun formatToDisplayDate(timestamp: Long): String {
        return try {
            displayDateFormat.format(Date(timestamp))
        } catch (e: Exception) {
            "Invalid Date"
        }
    }

    // Format timestamp to API date string
    fun formatToApiDate(timestamp: Long): String {
        return try {
            apiDateFormat.format(Date(timestamp))
        } catch (e: Exception) {
            ""
        }
    }

    // Parse API date string to timestamp
    fun parseApiDate(dateString: String): Long {
        return try {
            apiDateFormat.parse(dateString)?.time ?: 0L
        } catch (e: Exception) {
            0L
        }
    }

    // Get relative time (e.g., "2 hours ago")
    fun getRelativeTime(timestamp: Long): String {
        val now = System.currentTimeMillis()
        val diff = now - timestamp

        return when {
            diff < 60 * 1000 -> "Just now"
            diff < 60 * 60 * 1000 -> "${diff / (60 * 1000)} minutes ago"
            diff < 24 * 60 * 60 * 1000 -> "${diff / (60 * 60 * 1000)} hours ago"
            diff < 7 * 24 * 60 * 60 * 1000 -> "${diff / (24 * 60 * 60 * 1000)} days ago"
            diff < 30 * 24 * 60 * 60 * 1000 -> "${diff / (7 * 24 * 60 * 60 * 1000)} weeks ago"
            else -> formatToDisplayDate(timestamp)
        }
    }

    // Get current week range string
    fun getCurrentWeekRange(): String {
        val calendar = Calendar.getInstance()
        calendar.firstDayOfWeek = Calendar.MONDAY

        // Start of week (Monday)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val startOfWeek = calendar.time

        // End of week (Sunday)
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        val endOfWeek = calendar.time

        return "${shortDateFormat.format(startOfWeek)} - ${shortDateFormat.format(endOfWeek)}"
    }

    // Check if timestamp is today
    fun isToday(timestamp: Long): Boolean {
        val today = Calendar.getInstance()
        val targetDate = Calendar.getInstance().apply { timeInMillis = timestamp }

        return today.get(Calendar.YEAR) == targetDate.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == targetDate.get(Calendar.DAY_OF_YEAR)
    }

    // Check if timestamp is this week
    fun isThisWeek(timestamp: Long): Boolean {
        val today = Calendar.getInstance()
        val targetDate = Calendar.getInstance().apply { timeInMillis = timestamp }

        today.firstDayOfWeek = Calendar.MONDAY
        targetDate.firstDayOfWeek = Calendar.MONDAY

        return today.get(Calendar.YEAR) == targetDate.get(Calendar.YEAR) &&
                today.get(Calendar.WEEK_OF_YEAR) == targetDate.get(Calendar.WEEK_OF_YEAR)
    }
}
