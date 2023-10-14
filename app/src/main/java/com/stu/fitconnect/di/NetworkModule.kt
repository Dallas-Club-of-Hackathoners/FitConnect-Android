package com.stu.fitconnect.di

import com.stu.fitconnect.network.ApiConstants
import com.stu.fitconnect.network.spotclubs.api.SportClubsApiService
import com.stu.fitconnect.network.usersource.api.UsersApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSportClubsApiService(retrofit: Retrofit): SportClubsApiService {
        return retrofit.create(SportClubsApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideUsersApiService(retrofit: Retrofit): UsersApiService {
        return retrofit.create(UsersApiService::class.java)
    }
}