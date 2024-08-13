package com.mycheva.app.forum.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.forum.main.data.GetForumsResponse
import com.mycheva.app.forum.main.domain.ForumRepository
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
class ForumScreenViewModel @Inject constructor(
    private val repository: ForumRepository
) : ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(ForumScreenState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ForumScreenState())

    fun onEvent(event: ForumScreenEvent) {
        when (event) {
            ForumScreenEvent.OnClearNotification ->
                _state.update { it.copy(isLoading = false, isRequestError = false, notificationMessage = "") }

            is ForumScreenEvent.OnLoadForums -> loadForums(event.token)
        }
    }

    private fun loadForums(token: String) {
        _state.update { it.copy(isLoading = true) }
        val request = repository.getForums("Bearer $token")
        request.enqueue(
            object : Callback<GetForumsResponse> {
                override fun onResponse(
                    call: Call<GetForumsResponse>,
                    response: Response<GetForumsResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> _state.update { it.copy(
                            posts = response.body()!!.forums
                        ) }

                        else -> _state.update { it.copy(
                            isRequestError = true,
                            notificationMessage = "Server error."
                        ) }
                    }
                }

                override fun onFailure(p0: Call<GetForumsResponse>, p1: Throwable) {
                    _state.update { it.copy(
                        isRequestError = true,
                        notificationMessage = "Server error."
                    ) }
                }

            }
        )
    }

}