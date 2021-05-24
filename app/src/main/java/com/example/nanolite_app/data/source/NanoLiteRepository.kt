package com.example.nanolite_app.data.source

import com.example.nanolite_app.data.source.remote.RemoteDataSource
import com.example.nanolite_app.domain.model.User
import com.example.nanolite_app.domain.repository.INanoLiteRepository
import javax.inject.Inject

class NanoLiteRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
): INanoLiteRepository {
    override fun setUserAuthentication(user: User) {
        remoteDataSource.setUserAuthentication(user)
    }
}