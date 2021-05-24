package com.example.nanolite_app.data.source.remote

import com.example.nanolite_app.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult

interface IAuthentication {
    suspend fun setUserAuthentication(user: User): Task<AuthResult>
    suspend fun signIn(email: String, password: String): Task<AuthResult>
    fun logout(): Boolean
}