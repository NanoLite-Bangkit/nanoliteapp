package com.example.nanolite_app.data.source

import com.example.nanolite_app.data.source.remote.RemoteDataSource
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.repository.INanoLiteRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NanoLiteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): INanoLiteRepository {

    override suspend fun setUserAuthentication(user: User): Task<AuthResult> {
        return remoteDataSource.setUserAuthentication(user)
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
        return remoteDataSource.signIn(email, password)
    }

    override fun signOut(): Boolean {
        return remoteDataSource.logout()
    }
}