package com.mycheva.app.forum.comment.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CommentViewModel: ViewModel() {

    private val _state = MutableStateFlow(CommentScreenState())
    val state = _state.asStateFlow()

    fun onEvent(event: CommentEvent) {
        when (event) {
            is CommentEvent.OnCommentTextChange -> _state.update {
                it.copy(commentText = event.value)
            }
            CommentEvent.OnClearState -> TODO()
        }
    }


}