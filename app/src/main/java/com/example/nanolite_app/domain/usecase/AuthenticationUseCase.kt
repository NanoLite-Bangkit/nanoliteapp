package com.example.nanolite_app.domain.usecase

import com.example.nanolite_app.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthenticationUseCase {
    suspend fun setUserAuthentication(user: User): Task<AuthResult>

    suspend fun signIn(email: String, password: String): Task<AuthResult>



    fun signOut(): Boolean
}