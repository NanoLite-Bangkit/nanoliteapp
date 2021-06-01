package com.example.nanolite_app.data.source

import androidx.lifecycle.LiveData
import com.example.nanolite_app.data.source.local.LocalDataSource
import com.example.nanolite_app.data.source.local.entity.ScanningEntity
import com.example.nanolite_app.data.source.remote.RemoteDataSource
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.repository.INanoLiteRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NanoLiteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): INanoLiteRepository {

    override suspend fun setUserAuthentication(user: User): Task<AuthResult> {
        return remoteDataSource.setUserAuthentication(user)
    }

    override suspend fun signIn(email: String, password: String): Task<AuthResult> {
        return remoteDataSource.signIn(email, password)
    }

    override suspend fun signOut() {
        return remoteDataSource.logout()
    }

    override fun insertUserData(name: String, email: String) {
        remoteDataSource.insertUserData(name, email)
    }

    override fun getUserData(email: String) : DocumentReference {
        return remoteDataSource.getUserData(email)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return remoteDataSource.getCurrentUser()
    }

    override suspend fun insertScanningResult(scanningEntity: ScanningEntity) {
        localDataSource.insertScanningResult(scanningEntity)
    }

    override fun getScanningHistory(email: String): Flow<List<ScanningEntity>> {
        return localDataSource.getScanningHistory(email)
    }

    override fun getScanningResult(date: String): Flow<List<ScanningEntity>> {
        return localDataSource.getScanningResult(date)
    }
}