package com.stu.fitconnect.di

import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepository
import com.stu.fitconnect.features.authentication.repositories.AuthenticationRepositoryImpl
import com.stu.fitconnect.features.sportclubs.repositories.SportClubRepository
import com.stu.fitconnect.features.sportclubs.repositories.SportClubRepositoryImpl
import com.stu.fitconnect.features.sportclubs.repositories.SportClubsRepository
import com.stu.fitconnect.features.sportclubs.repositories.SportClubsRepositoryImpl
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

    @Binds
    abstract fun bindSportClubRepository(
        sportClubRepository: SportClubRepositoryImpl
    ): SportClubRepository
}