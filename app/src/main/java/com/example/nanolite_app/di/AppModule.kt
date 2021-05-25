package com.example.nanolite_app.di

import com.example.nanolite_app.domain.usecase.NanoLiteInteractor
import com.example.nanolite_app.domain.usecase.NanoLiteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    abstract fun providAuthenticationUsecase(nanoLiteInteractor: NanoLiteInteractor): NanoLiteUseCase
}