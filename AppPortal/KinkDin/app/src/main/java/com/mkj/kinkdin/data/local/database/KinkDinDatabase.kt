package com.mkj.kinkdin.data.local.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mkj.kinkdin.data.local.dao.LeaderboardDao
import com.mkj.kinkdin.data.local.dao.StatsDao
import com.mkj.kinkdin.data.local.dao.UserDao
import com.mkj.kinkdin.data.local.entities.LeaderboardEntity
import com.mkj.kinkdin.data.local.entities.StatsEntity
import com.mkj.kinkdin.data.local.entities.UserEntity
import com.mkj.kinkdin.util.Constants

@Database(
    entities = [
        UserEntity::class,
        LeaderboardEntity::class,
        StatsEntity::class
    ],
    version = Constants.DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(DatabaseConverters::class)
abstract class KinkdinDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun leaderboardDao(): LeaderboardDao
    abstract fun statsDao(): StatsDao

    companion object {
        const val DATABASE_NAME = Constants.DATABASE_NAME
    }
}
