package com.stu.fitconnect.di

import com.stu.fitconnect.base.ResourceManager
import com.stu.fitconnect.di.UtilsModule_ProvideResourceJsonManagerFactory.provideResourceJsonManager
import com.stu.fitconnect.features.sportclubs.presentation.FiltersManager
import com.stu.fitconnect.utils.ResourceJsonManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FilterManagerModule {

    @Provides
    @Singleton
    fun provideFilterManager(resourceJsonManager: ResourceJsonManager): FiltersManager {
        return FiltersManager(resourceJsonManager)
    }
}