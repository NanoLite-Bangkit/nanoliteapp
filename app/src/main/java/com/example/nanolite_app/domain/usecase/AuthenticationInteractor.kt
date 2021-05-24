package com.example.nanolite_app.domain.usecase

import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.repository.INanoLiteRepository
import javax.inject.Inject


class AuthenticationInteractor @Inject constructor(private val nanoLiteRepository: INanoLiteRepository): AuthenticationUseCase {
    override fun setUserAuthentication(user: User) {
        nanoLiteRepository.setUserAuthentication(user)
    }
}