package com.example.nanolite_app.data.source.remote

import com.example.nanolite_app.domain.model.User
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val authentication: FirebaseAuth) {


    fun setUserAuthentication(user: User){
        authentication.createUserWithEmailAndPassword(user.email, user.password)
    }
}