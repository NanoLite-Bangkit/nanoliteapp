package com.example.nanolite_app.domain.repository

import com.example.nanolite_app.domain.model.User

interface INanoLiteRepository {
    fun setUserAuthentication(user: User)
}