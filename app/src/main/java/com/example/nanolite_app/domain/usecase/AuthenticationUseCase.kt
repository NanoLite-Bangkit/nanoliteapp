package com.example.nanolite_app.domain.usecase

import com.example.nanolite_app.domain.model.User

interface AuthenticationUseCase {
    fun setUserAuthentication(user: User)
}