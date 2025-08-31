package com.mkj.kinkdin.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mkj.kinkdin.domain.model.UserRole

class DatabaseConverters {

    private val gson = Gson()

    @TypeConverter
    fun fromUserRole(role: UserRole): String {
        return role.name
    }

    @TypeConverter
    fun toUserRole(roleString: String): UserRole {
        return try {
            UserRole.valueOf(roleString)
        } catch (e: Exception) {
            UserRole.USER
        }
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return try {
            val listType = object : TypeToken<List<String>>() {}.type
            gson.fromJson(value, listType)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
