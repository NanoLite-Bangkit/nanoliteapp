package com.example.nanolite_app.data.source.remote

import com.example.nanolite_app.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val authentication: FirebaseAuth
): IAuthentication {


    override suspend fun setUserAuthentication(user: User): Task<AuthResult>{
        return authentication.createUserWithEmailAndPassword(user.email, user.password)
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult>{
        return authentication.signInWithEmailAndPassword(email, password)
    }

    override fun logout(): Boolean{
        authentication.signOut()
        return true
    }
}