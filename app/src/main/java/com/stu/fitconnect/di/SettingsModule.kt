package com.example.smartmessenger.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule {

//    @Binds
//    abstract fun bindAppSettings(
//        appSettings: SharedPreferencesAppSettings
//    ): AppSettings

}
