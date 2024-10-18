package com.mycheva.app.announcement.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.announcement.data.AnnouncementResponse
import com.mycheva.app.announcement.data.AnnouncementsItem
import com.mycheva.app.announcement.domain.AnnouncementRepositoryImpl
import com.mycheva.app.core.database.BookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val repo: AnnouncementRepositoryImpl
): ViewModel() {

    private val _token = repo.getToken()
    private val _state = MutableStateFlow(AnnouncementScreenState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AnnouncementScreenState())

    fun onEvent(event: AnnouncementScreenEvent) {
        when(event) {
            AnnouncementScreenEvent.OnClearState -> clearState()

            is AnnouncementScreenEvent.OnAddBookmark -> addBookmark(event.announcement)

            is AnnouncementScreenEvent.OnLoadAnnouncements -> loadAnnouncements(event.token)
        }
    }

    private fun addBookmark(announcement: AnnouncementsItem) {
        val data = BookmarkEntity(
            profileUrl = announcement.user.profileUrl,
            name = announcement.user.name,
            timeStamp = announcement.createdAt,
            imageUrl = if (announcement.imageUrl == null) "" else announcement.imageUrl,
            title = announcement.title,
            content = announcement.content
        )

        viewModelScope.launch(Dispatchers.IO) {
            val save = repo.addBookmark(data)
            if (save) _state.update { it.copy(notificationMessage = "Successfully saved to bookmark.") }
            else _state.update { it.copy(notificationMessage = "Can't save to bookmark", isRequestError = true) }
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                notificationMessage = "",
                isRequestError = false,
            )
        }
    }

    private fun loadAnnouncements(token: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAnnouncements("Bearer $token")
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            announcements = result,
                            isLoading = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            notificationMessage = error.message.toString(),
                            isRequestError = true,
                            isLoading = false
                        )
                    }
                }
        }
    }

}