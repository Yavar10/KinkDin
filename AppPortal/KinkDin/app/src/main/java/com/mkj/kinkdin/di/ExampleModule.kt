package com.mkj.kinkdin.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExampleModule {
    @Provides
    @Singleton
    fun provideGreeting(): String = "Hello, Hilt World!"
}