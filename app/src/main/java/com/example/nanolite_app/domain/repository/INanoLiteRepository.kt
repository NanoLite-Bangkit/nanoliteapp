package com.example.nanolite_app.domain.repository

import com.example.nanolite_app.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface INanoLiteRepository {
    suspend fun setUserAuthentication(user: User): Task<AuthResult>

    suspend fun signIn(email: String, password: String): Task<AuthResult>



    fun signOut(): Boolean
}