package com.example.nanolite_app.data.source.remote

import android.util.Log
import com.example.nanolite_app.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val authentication: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): IAuthentication, IFirebaseFirestore {


    override suspend fun setUserAuthentication(user: User): Task<AuthResult>{
        return authentication.createUserWithEmailAndPassword(user.email, user.password)
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult>{
        return authentication.signInWithEmailAndPassword(email, password)
    }

    override suspend fun logout(){
        authentication.signOut()
    }

    override fun getCurrentUser(): FirebaseUser? {
        return authentication.currentUser
    }

    override fun insertUserData(name: String, email: String) {
        val user = HashMap<String, Any>()
        user["name"] = name
        user["email"] = email

        firebaseFirestore
            .collection("users")
            .document(email)
            .set(user)
    }

    override fun getUserData(email: String): DocumentReference {
        return firebaseFirestore.collection("users").document(email)
    }
}