package com.example.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


data class MovieResult(
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val poster_path: String,
    val title: String,
)


