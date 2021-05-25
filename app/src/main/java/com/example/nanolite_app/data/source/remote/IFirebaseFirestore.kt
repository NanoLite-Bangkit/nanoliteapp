package com.example.nanolite_app.data.source.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.auth.User

interface IFirebaseFirestore {
    fun insertUserData(name: String, email:String)

    fun getUserData(email: String): DocumentReference
}