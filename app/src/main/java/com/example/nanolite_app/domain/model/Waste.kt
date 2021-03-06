package com.example.nanolite_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Waste(
        var id: Int? = null,
        var email: String,
        var trashName: String,
        var date: String,
        var imageUri: String,
        var classification: String
): Parcelable