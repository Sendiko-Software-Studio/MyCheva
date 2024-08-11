package com.mycheva.app.dashboard.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycheva.app.dashboard.domain.DashboardRepository
import com.mycheva.app.profile.main.data.GetUserResponse
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
class DashboardScreenViewModel @Inject constructor(
    private val dashboardRepository: DashboardRepository
): ViewModel() {

    private val _token = dashboardRepository.getToken()
    private val _userId = dashboardRepository.getUserId()
    private val _state = MutableStateFlow(DashboardScreenState())
    val state = combine(_token, _userId, _state) { token, userId, state ->
        state.copy(
            token = token,
            userId = userId
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), DashboardScreenState())

    fun onEvent(event: DashboardScreenEvent) {
        when (event) {
            DashboardScreenEvent.OnClearState -> TODO()
            is DashboardScreenEvent.OnGetDashboardData -> getUserData(event.token, event.userId)
        }
    }

    private fun getUserData(token: String, userId: String) {
        _state.update { it.copy(isLoading = true) }
        val request = dashboardRepository.getUser(token = "Bearer $token", userId = userId)
        request.enqueue(
            object : Callback<GetUserResponse> {
                override fun onResponse(
                    call: Call<GetUserResponse>,
                    response: Response<GetUserResponse>
                ) {
                    _state.update { it.copy(isLoading = false) }
                    when (response.code()) {
                        200 -> {
                            _state.update { it.copy(name = response.body()!!.user.name) }
                        }

                        else -> {
                            _state.update { it.copy(
                                isRequestFailed = true,
                                notificationMessage = "Server error."
                            ) }
                        }
                    }
                }

                override fun onFailure(p0: Call<GetUserResponse>, p1: Throwable) {
                    _state.update { it.copy(
                        isRequestFailed = true,
                        notificationMessage = "Server error."
                    ) }
                }

            }
        )
    }

}