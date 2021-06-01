package com.example.nanolite_app.domain.usecase

import androidx.lifecycle.LiveData
import com.example.nanolite_app.data.source.local.entity.ScanningEntity
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.repository.INanoLiteRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class NanoLiteInteractor @Inject constructor(private val nanoLiteRepository: INanoLiteRepository): NanoLiteUseCase {
    override suspend fun setUserAuthentication(user: User): Task<AuthResult> {
        return nanoLiteRepository.setUserAuthentication(user)
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
        return nanoLiteRepository.signIn(email, password)
    }

    override suspend fun signOut() {
        return nanoLiteRepository.signOut()
    }

    override fun insertUserData(name: String, email: String){
        nanoLiteRepository.insertUserData(name, email)
    }

    override fun getUserData(email: String): DocumentReference {
        return nanoLiteRepository.getUserData(email)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return nanoLiteRepository.getCurrentUser()
    }

    override suspend fun insertScanningResult(scanningEntity: ScanningEntity) {
        nanoLiteRepository.insertScanningResult(scanningEntity)
    }

    override fun getScanningHistory(email: String): Flow<List<ScanningEntity>> {
        return nanoLiteRepository.getScanningHistory(email)
    }

    override fun getScanningResult(date: String):  Flow<List<ScanningEntity>> {
        return nanoLiteRepository.getScanningResult(date)
    }
}