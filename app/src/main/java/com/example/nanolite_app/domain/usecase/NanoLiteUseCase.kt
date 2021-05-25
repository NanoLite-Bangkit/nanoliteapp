package com.example.nanolite_app.domain.usecase

import com.example.nanolite_app.data.source.local.entity.ScanningEntity
import com.example.nanolite_app.domain.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.coroutines.flow.Flow

interface NanoLiteUseCase {
    suspend fun setUserAuthentication(user: User): Task<AuthResult>

    suspend fun signIn(email: String, password: String): Task<AuthResult>

    suspend fun signOut()

    fun insertUserData(name: String, email: String)

    fun getUserData(email: String): DocumentReference

    fun getCurrentUser(): FirebaseUser?

    suspend fun insertScanningResult(scanningEntity: ScanningEntity)

    fun getScanningHistory(email: String): Flow<List<ScanningEntity>>
}