package com.example.nanolite_app.di

import android.content.Context
import androidx.room.Room
import com.example.nanolite_app.data.source.local.room.NanoliteDatabase
import com.example.nanolite_app.data.source.local.room.ScanningDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NanoliteDatabase =
        Room.databaseBuilder(
            context,
            NanoliteDatabase::class.java,
            "nanolite_database"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideScanningDao(database: NanoliteDatabase): ScanningDao = database.scanningDao()
}