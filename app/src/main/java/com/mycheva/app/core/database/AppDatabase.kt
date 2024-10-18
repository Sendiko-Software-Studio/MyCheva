package com.mycheva.app.core.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        BookmarkEntity::class
    ],
    version = 2
)
abstract class AppDatabase: RoomDatabase() {
    abstract val bookmarkDao: BookmarkDao
}