package com.mycheva.app.announcement.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.announcement.data.AnnouncementRepositoryImpl
import com.mycheva.app.announcement.domain.Announcement
import com.mycheva.app.core.database.BookmarkEntity
import com.mycheva.app.core.network.utils.onError
import com.mycheva.app.core.network.utils.onSuccess
import com.mycheva.app.core.ui.utils.UiText
import com.mycheva.app.core.ui.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val repo: AnnouncementRepositoryImpl
): ViewModel() {

    private val _token = repo.getToken()
    private val _state = MutableStateFlow(AnnouncementState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AnnouncementState())

    fun onEvent(event: AnnouncementEvent) {
        when(event) {
            AnnouncementEvent.OnClearState -> clearState()

            is AnnouncementEvent.OnAddBookmark -> addBookmark(event.announcement)

            is AnnouncementEvent.OnLoadAnnouncements -> loadAnnouncements(event.token)
        }
    }

    private fun addBookmark(announcement: Announcement) {

        viewModelScope.launch(Dispatchers.IO) {
            val save = repo.addBookmark(announcement)
            if (save)
                _state.update {
                    it.copy(
                        notificationMessage = UiText.DynamicString("Successfully saved to bookmark.")
                    )
                }
            else _state.update {
                it.copy(
                    notificationMessage = UiText.DynamicString("Can't save to bookmark"),
                    isRequestError = true
                )
            }
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                notificationMessage = UiText.DynamicString(""),
                isRequestError = false,
            )
        }
    }

    private fun loadAnnouncements(token: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repo.getAnnouncements(token)
                .onSuccess { result ->
                    _state.update { state ->
                        state.copy(
                            announcements = result.map { AnnouncementUi.from(it) },
                            isLoading = false
                        )
                    }
                }
                .onError { error ->
                    _state.update {
                        it.copy(
                            notificationMessage = error.asUiText(),
                            isRequestError = true,
                            isLoading = false
                        )
                    }
                }
        }
    }

}