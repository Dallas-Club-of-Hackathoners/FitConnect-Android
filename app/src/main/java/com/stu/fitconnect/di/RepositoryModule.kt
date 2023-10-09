package com.example.smartmessenger.di

import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepository
import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepositoryImpl
import com.stu.fitconnect.features.sportsclubs.repositories.SportClubsRepository
import com.stu.fitconnect.features.sportsclubs.repositories.SportClubsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAuthenticationRepository(
        accountsRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository

    @Binds
    abstract fun bindSportsClubsRepository(
        sportsClubsRepository: SportClubsRepositoryImpl
    ): SportClubsRepository


}