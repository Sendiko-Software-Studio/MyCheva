package com.mycheva.app.core.database

import android.content.Context
import androidx.room.Room

object RoomFactory {

    fun create(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db")
            .build()
    }

}