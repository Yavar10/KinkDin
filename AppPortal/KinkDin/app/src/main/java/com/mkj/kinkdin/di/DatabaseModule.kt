package com.mkj.kinkdin.di

import android.content.Context
import androidx.room.Room
import com.mkj.kinkdin.data.local.dao.LeaderboardDao
import com.mkj.kinkdin.data.local.dao.StatsDao
import com.mkj.kinkdin.data.local.dao.UserDao
import com.mkj.kinkdin.data.local.database.KinkdinDatabase
import com.mkj.kinkdin.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideKinkdinDatabase(
        @ApplicationContext context: Context
    ): KinkdinDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            KinkdinDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideUserDao(database: KinkdinDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideLeaderboardDao(database: KinkdinDatabase): LeaderboardDao {
        return database.leaderboardDao()
    }

    @Provides
    fun provideStatsDao(database: KinkdinDatabase): StatsDao {
        return database.statsDao()
    }
}