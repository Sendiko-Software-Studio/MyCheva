package com.mycheva.app.bookmark.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mycheva.app.bookmark.domain.Bookmark

@Entity(tableName = "bookmarks")
data class BookmarkEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "profileUrl")
    val profileUrl: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "timeStamp")
    val timeStamp: String,

    @ColumnInfo(name = "content")
    val content: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String = "",
)

fun BookmarkEntity.toBookmark(): Bookmark {
    return Bookmark(
        id = id.toString(),
        profileUrl = profileUrl,
        username = name,
        time = timeStamp,
        content = content,
        title = title,
    )
}

fun Bookmark.toEntity(): BookmarkEntity {
    return BookmarkEntity(
        id = id.toInt(),
        profileUrl = profileUrl,
        name = username,
        timeStamp = time,
        content = content,
        title = title,
    )
}