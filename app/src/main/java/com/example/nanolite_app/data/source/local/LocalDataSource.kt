package com.example.nanolite_app.data.source.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nanolite_app.data.source.local.entity.ScanningEntity
import com.example.nanolite_app.data.source.local.room.ScanningDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val scanningDao: ScanningDao) {

    suspend fun insertScanningResult(scanningEntity: ScanningEntity){
        scanningDao.insert(scanningEntity)
    }

    fun getScanningHistory(email: String): Flow<List<ScanningEntity>> {
        return scanningDao.getHistory(email)
    }

    fun getScanningResult(date: String): Flow<List<ScanningEntity>> {
        return scanningDao.getScanningResult(date)
    }
}