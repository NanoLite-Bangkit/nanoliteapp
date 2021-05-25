package com.example.nanolite_app.data.source.local.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nanolite_app.data.source.local.entity.ScanningEntity

@Database(entities = [ScanningEntity::class], version = 1, exportSchema = false)
abstract class NanoliteDatabase: RoomDatabase() {
    abstract fun scanningDao(): ScanningDao
}