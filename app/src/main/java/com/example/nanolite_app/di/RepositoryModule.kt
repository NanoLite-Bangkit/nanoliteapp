package com.example.nanolite_app.di

import com.example.nanolite_app.data.source.NanoLiteRepository
import com.example.nanolite_app.domain.repository.INanoLiteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [FirebaseModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(nanoLiteRepository: NanoLiteRepository): INanoLiteRepository
}