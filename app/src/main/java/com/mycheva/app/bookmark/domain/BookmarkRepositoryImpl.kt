package com.mycheva.app.bookmark.domain

import com.mycheva.app.core.database.BookmarkDao
import com.mycheva.app.core.database.BookmarkEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BookmarkRepositoryImpl @Inject constructor(
    private val dao: BookmarkDao
): BookmarkRepository {

    override suspend fun addBookmark(bookmarkEntity: BookmarkEntity): Result<Boolean> {
        return suspendCoroutine { continuation ->
            try {
                dao.addBookmark(bookmarkEntity)
            } catch (exception: Exception) {
                continuation.resume(Result.failure(exception))
            }
        }
    }

    override suspend fun removeBookmark(bookmarkEntity: BookmarkEntity): Result<Boolean> {
        return suspendCoroutine { continuation ->
            try {
                dao.removeBookmarks(bookmarkEntity)
                continuation.resume(Result.success(true))
            } catch (exception: Exception) {
                continuation.resume(Result.failure(exception))
            }
        }
    }

    override fun getBookmarks(): Flow<List<BookmarkEntity>> {
        return dao.getBookmarks()
    }


}