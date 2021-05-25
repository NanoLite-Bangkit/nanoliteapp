package com.example.nanolite_app.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nanolite_app.data.source.local.entity.ScanningEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanningDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entiy: ScanningEntity)

    @Query("SELECT * FROM scanning_history where email = :email")
    fun getHistory(email: String): Flow<List<ScanningEntity>>
}