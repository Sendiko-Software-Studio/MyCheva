package com.mycheva.app.profile.main.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.profile.main.data.GetUserResponse
import com.mycheva.app.profile.main.data.User
import com.mycheva.app.profile.main.domain.ProfileRepository
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
class ProfileScreenViewModel @Inject constructor(
    private val repository: ProfileRepository
): ViewModel() {

    private val _token = repository.getToken()
    private val _state = MutableStateFlow(ProfileScreenState())
    val state = combine(_token, _state) { token, state ->
        state.copy(token = token)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ProfileScreenState())

    fun onEvent(event: ProfileScreenEvent) {
        when (event) {
            ProfileScreenEvent.OnClearState -> clearState()
            ProfileScreenEvent.OnEditProfile -> TODO()
            is ProfileScreenEvent.OnLogout -> logout(event.token)
            is ProfileScreenEvent.OnGetProfile -> getProfile(event.token)
            }
        }

    private fun logout(token: String) {
        _state.update { it.copy(isLoading = true) }
        val bearerToken = "Bearer $token"
        val request = repository.logout(bearerToken)
        viewModelScope.launch(Dispatchers.IO) {
            if (request.execute().isSuccessful){
                repository.deleteToken()
                _state.update {
                    it.copy(
                        isLoading = false,
                        isError = false,
                        notificationMessage = "Logout success.",
                        isLogoutSuccess = true
                    )
                }
            } else {
                _state.update {
                    it.copy(
                        isError = true,
                        notificationMessage = "Something went wrong."
                    )
                }
            }
        }
    }

    private fun getProfile(token: String) {
        _state.update { it.copy(isLoading = true) }
        val bearerToken = "Bearer $token"
        val request = repository.getUser(bearerToken)
        request.enqueue(
            object : Callback<GetUserResponse> {
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when(response.code()) {
                        200 -> _state.update {
                            it.copy(
                                user = User(
                                    name = response.body()?.data?.nama ?: "",
                                    username = response.body()?.data?.username ?: "",
                                    nim = response.body()?.data?.nim ?: "",
                                    faculty = response.body()?.data?.fakultas ?: "",
                                    major = response.body()?.data?.prodi ?: "",
                                    division = response.body()?.data?.divisi ?: "",
                                    imageUrl = response.body()?.data?.urlFotoProfile ?: ""
                                )
                            )
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