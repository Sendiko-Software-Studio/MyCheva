package com.mycheva.app.bookmark.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.bookmark.data.BookmarkRepositoryImpl
import com.mycheva.app.bookmark.domain.Bookmark
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookmarkViewModel(
    private val repository: BookmarkRepositoryImpl
) : ViewModel() {

    private val _bookmarks = repository.getBookmarks()
    private val _state = MutableStateFlow(BookmarkState())
    val state = combine(_state, _bookmarks) { state, bookmarks ->
        state.copy(bookmarks = bookmarks.map { BookmarkUi.fromDomain(it) })
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), BookmarkState())

    fun onEvent(event: BookmarkEvent) {
        when (event) {
            is BookmarkEvent.OnRemoveBookmark -> removeBookmark(event.bookmark)
        }
    }

    private fun removeBookmark(bookmark: Bookmark) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.removeBookmark(bookmark)
        }
    }

}