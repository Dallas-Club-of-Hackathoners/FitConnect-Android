package com.stu.fitconnect.di

import android.content.Context
import com.stu.fitconnect.utils.ResourceJsonManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UtilsModule {

    @Provides
    @Singleton
    fun provideResourceJsonManager(@ApplicationContext context: Context): ResourceJsonManager {
        return ResourceJsonManager(context)
    }
}