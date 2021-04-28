package com.sung.doordash.di

import com.sung.doordash.data.remote.DoorDashService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RemoteServiceModule {

    @Singleton
    @Provides
    fun providesDoorDashService(): DoorDashService {
        return DoorDashService.create()
    }
}