package com.example.nanolite_app.domain.usecase

import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.repository.INanoLiteRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AuthenticationInteractor @Inject constructor(private val nanoLiteRepository: INanoLiteRepository): AuthenticationUseCase {
    override suspend fun setUserAuthentication(user: User): Task<AuthResult> {
        return nanoLiteRepository.setUserAuthentication(user)
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
        return nanoLiteRepository.signIn(email, password)
    }

    override fun signOut(): Boolean {
        return nanoLiteRepository.signOut()
    }
}