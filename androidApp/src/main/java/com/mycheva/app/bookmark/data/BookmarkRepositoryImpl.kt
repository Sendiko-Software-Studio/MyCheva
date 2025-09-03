package com.mycheva.app.bookmark.data

import com.mycheva.app.bookmark.domain.Bookmark
import com.mycheva.app.bookmark.domain.BookmarkRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BookmarkRepositoryImpl(
    private val dao: BookmarkDao
): BookmarkRepository {

    override suspend fun addBookmark(bookmark: Bookmark): Result<Boolean> {
        return suspendCoroutine { continuation ->
            try {
                dao.addBookmark(bookmark.toEntity())
            } catch (exception: Exception) {
                continuation.resume(Result.failure(exception))
            }
        }
    }

    override suspend fun removeBookmark(bookmark: Bookmark): Result<Boolean> {
        return suspendCoroutine { continuation ->
            try {
                dao.removeBookmarks(bookmark.toEntity())
                continuation.resume(Result.success(true))
            } catch (exception: Exception) {
                continuation.resume(Result.failure(exception))
            }
        }
    }

    override fun getBookmarks(): Flow<List<Bookmark>> {
        return dao.getBookmarks().map { it.map { it.toBookmark() } }
    }


}