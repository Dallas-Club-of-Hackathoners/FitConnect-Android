package com.example.smartmessenger.di

import com.stu.fitconnect.network.authentication.AuthenticationSource
import com.stu.fitconnect.network.authentication.FirebaseAuthenticationSource
import com.stu.fitconnect.network.spotclubs.source.SportClubsSource
import com.stu.fitconnect.network.spotclubs.source.SportClubsSourceImpl
import com.stu.fitconnect.network.usersource.UserSourceImpl
import com.stu.fitconnect.network.usersource.UsersSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindAuthenticationSource(
        firebaseAuthenticationSource: FirebaseAuthenticationSource
    ): AuthenticationSource

    @Binds
    abstract fun bindUsersSource(
        chatsSource: UserSourceImpl
    ): UsersSource

    @Binds
    abstract fun bindSportClubsSource(
        sportClubsSource: SportClubsSourceImpl
    ): SportClubsSource

}