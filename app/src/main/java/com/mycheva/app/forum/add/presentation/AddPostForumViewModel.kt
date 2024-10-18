package com.mycheva.app.forum.add.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.forum.add.data.ForumPostRequest
import com.mycheva.app.forum.add.data.ForumPostResponse
import com.mycheva.app.forum.add.domain.AddPostRepository
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
class AddPostForumViewModel @Inject constructor(
    private val repository: AddPostRepository
) : ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(AddPostForumState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(token = token, userId = userId)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AddPostForumState())

    fun onEvent(event: AddPostForumEvent) {
        when (event) {
            AddPostForumEvent.OnClearState -> clearState()
            is AddPostForumEvent.OnPostTextChanged -> changePostText(event.text)
            is AddPostForumEvent.OnPost -> post(event.token, event.userId, event.content)
        }
    }

    private fun changePostText(value: String) {
        _state.update {
            it.copy(
                postText = value
            )
        }
    }

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                isRequestError = false,
                isRequestSuccess = false,
                notificationMessage = ""
            )
        }
    }

    private fun post(token: String, userId: String, content: String) {
        _state.update { it.copy(isLoading = true) }
        val data = ForumPostRequest(
            userId = userId.toInt(),
            content = content
        )
        val request = repository.postForum("Bearer $token", data)
        request.enqueue(
            object : Callback<ForumPostResponse> {
                override fun onResponse(
                    call: Call<ForumPostResponse>,
                    response: Response<ForumPostResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        201 -> _state.update {
                            it.copy(
                                isRequestSuccess = true,
                                 notificationMessage = "Posted successfully."
                            )
                        }

                        else -> _state.update { it.copy(
                            isRequestError = true,
                            notificationMessage = "Server error."
                        ) }
                    }
                }

                override fun onFailure(p0: Call<ForumPostResponse>, p1: Throwable) {
                    _state.update { it.copy(
                        isRequestError = true,
                        notificationMessage = "Server error."
                    ) }
                }

            }
        )

    }

}