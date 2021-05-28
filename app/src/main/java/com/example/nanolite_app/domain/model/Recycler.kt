package com.example.nanolite_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recycler(
    var jenisSampah: String,
    var link: String
): Parcelable