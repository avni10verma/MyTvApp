package com.example.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class MovieResult(

    val id: Int,
    val overview: String,
    val poster_path: String,
    val title: String,
)


