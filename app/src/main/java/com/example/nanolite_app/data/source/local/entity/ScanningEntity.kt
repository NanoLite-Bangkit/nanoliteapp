package com.example.nanolite_app.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scanning_history")
data class ScanningEntity(
    @PrimaryKey
    @NonNull
    var email: String,

    @ColumnInfo(name = "userName")
    var userName: String,

    @ColumnInfo(name = "trashName")
    var trashName: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "imageUri")
    var imageUri: String
)
