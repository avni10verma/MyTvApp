// MovieDbHelper.kt
package com.example.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns
import com.example.data.model.MovieDatabaseContract

private const val SQL_CREATE_ENTRIES =
    "CREATE TABLE ${MovieDatabaseContract.MovieEntry.TABLE_NAME} (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY," +
            "${MovieDatabaseContract.MovieEntry.COLUMN_NAME_ID} INTEGER," +
            "${MovieDatabaseContract.MovieEntry.COLUMN_NAME_TITLE} TEXT," +
            "${MovieDatabaseContract.MovieEntry.COLUMN_NAME_OVERVIEW} TEXT," +
            "${MovieDatabaseContract.MovieEntry.COLUMN_NAME_POSTER_PATH} TEXT)" // Add this line

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${MovieDatabaseContract.MovieEntry.TABLE_NAME}"

class MovieDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "MovieDatabase.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }
}
