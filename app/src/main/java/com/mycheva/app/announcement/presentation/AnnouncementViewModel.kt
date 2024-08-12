package com.mycheva.app.announcement.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.announcement.data.AnnouncementResponse
import com.mycheva.app.announcement.domain.AnnouncementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AnnouncementViewModel @Inject constructor(
    private val repo: AnnouncementRepository
): ViewModel() {

    private val _token = repo.getToken()
    private val _state = MutableStateFlow(AnnouncementScreenState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AnnouncementScreenState())

    fun onEvent(event: AnnouncementScreenEvent) {
        when(event) {
            AnnouncementScreenEvent.OnClearState -> _state.update { it.copy(
                isLoading = false,
                notificationMessage = "",
                isRequestError = false,
            ) }

            is AnnouncementScreenEvent.OnAddBookmark -> TODO()

            is AnnouncementScreenEvent.OnLoadAnnouncements -> loadAnnouncements(event.token)
        }
    }

    private fun loadAnnouncements(token: String) {
        _state.update { it.copy(isLoading = true) }
        val request = repo.getAnnouncements("Bearer $token")
        request.enqueue(
            object : Callback<AnnouncementResponse> {
                override fun onResponse(
                    call: Call<AnnouncementResponse>,
                    response: Response<AnnouncementResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> _state.update {
                            it.copy(announcements = response.body()!!.announcements)
                        }

                        else -> _state.update {
                            it.copy(isRequestError = true, notificationMessage = "Server error.")
                        }
                    }
                }

                override fun onFailure(call: Call<AnnouncementResponse>, throwable: Throwable) {
                    _state.update {
                        it.copy(isRequestError = true, notificationMessage = "Server error.")
                    }
                }

            }
        )
    }

}