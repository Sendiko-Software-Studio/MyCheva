package com.mycheva.app.forum.add.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.forum.add.data.ForumPostRequest
import com.mycheva.app.forum.add.domain.AddPostRepositoryImpl
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
class AddPostForumViewModel @Inject constructor(
    private val repository: AddPostRepositoryImpl
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

    private fun post(token: String, userId: String, content: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            val data = ForumPostRequest(
                userId = userId.toInt(),
                content = content
            )
            repository.postForum("Bearer $token", data)
                .onSuccess { result ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestSuccess = true,
                            notificationMessage = result.message
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            isLoading = false,
                            isRequestError = true,
                            notificationMessage = error.message.toString()
                        )
                    }
                }

        }

}