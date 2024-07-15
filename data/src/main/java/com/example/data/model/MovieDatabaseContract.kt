package com.example.data.model

import android.provider.BaseColumns

object MovieDatabaseContract {
    object MovieEntry : BaseColumns {
        const val TABLE_NAME = "movies"
        const val COLUMN_NAME_ID = "id"
        const val COLUMN_NAME_TITLE = "title"
        const val COLUMN_NAME_OVERVIEW = "overview"
        const val COLUMN_NAME_POSTER_PATH = "poster_path"
    }

}