package com.mycheva.app.profile.main.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.domain.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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
class ProfileScreenViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {

    private val _token = repository.getToken()
    private val _userId = repository.getUserId()
    private val _state = MutableStateFlow(ProfileScreenState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            id = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileScreenState())

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            ProfileScreenEvent.OnClearState -> clearState()
            ProfileScreenEvent.OnEditProfile -> TODO()
            is ProfileScreenEvent.OnLogout -> logout()
            is ProfileScreenEvent.OnGetProfile -> getProfile(event.token, event.userId)
            }
        }

    private fun logout() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteToken()
            delay(1000)
            _state.update {
                it.copy(
                    isLoading = false,
                    isError = false,
                    notificationMessage = "Logout success.",
                    isLogoutSuccess = true
                )
            }
        }
    }

    private fun getProfile(token: String, userId: String) {
        _state.update { it.copy(isLoading = true) }
        val bearerToken = "Bearer $token"
        val request = repository.getUser(bearerToken, userId)
        request.enqueue(
            object : Callback<GetUserResponse> {
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when(response.code()) {
                        200 -> {
                            Log.i("DEBUG", "onResponse: ${response.body()?.user?.userDatum}")
                            _state.update {
                                it.copy(
                                    name = response.body()?.user?.name?:"",
                                    username = response.body()?.user?.userDatum?.fullName ?: "",
                                    nim = response.body()?.user?.userDatum?.nim?:"",
                                    faculty = response.body()?.user?.userDatum?.faculty ?: "",
                                    major = response.body()?.user?.userDatum?.major ?: "",
                                    division = response.body()?.user?.userDatum?.division?.name ?:"",
                                    imageUrl = response.body()?.user?.userDatum?.imageUrl ?: ""
                                )
                            }
                        }

                        401 -> _state.update {
                            it.copy(
                                isError = true,
                                notificationMessage = "Unauthorized."
                            )
                        }

                        else -> _state.update {
                            it.copy(
                                isError = true,
                                notificationMessage = "Server error."
                            )
                        }
                    }
                }

                override fun onFailure(p0: Call<GetUserResponse>, p1: Throwable) {
                    _state.update {
                        it.copy(
                            isError = true,
                            notificationMessage = "Server error."
                        )
                    }
                }

            }
        )
}

    private fun clearState() {
        _state.update {
            it.copy(
                isLoading = false,
                isError = false,
                notificationMessage = ""
            )
        }
    }

}