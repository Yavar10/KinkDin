package com.mkj.kinkdin.di

import com.mkj.kinkdin.data.repository.AuthRepository
import com.mkj.kinkdin.data.repository.LeaderboardRepository
import com.mkj.kinkdin.data.repository.LeetcodeRepository
import com.mkj.kinkdin.data.repository.UserRepository
import com.mkj.kinkdin.domain.repository.IAuthRepository
import com.mkj.kinkdin.domain.repository.ILeaderboardRepository
import com.mkj.kinkdin.domain.repository.ILeetcodeRepository
import com.mkj.kinkdin.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindAuthRepository(
        authRepository: AuthRepository
    ): IAuthRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepository: UserRepository
    ): IUserRepository

    @Binds
    @Singleton
    abstract fun bindLeetcodeRepository(
        leetcodeRepository: LeetcodeRepository
    ): ILeetcodeRepository

//    @Binds
//    @Singleton
//    abstract fun bindLeaderboardRepository(
//        leaderboardRepository: LeaderboardRepository
//    ): ILeaderboardRepository
}
