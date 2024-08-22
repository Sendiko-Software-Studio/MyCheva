package com.mycheva.app.profile.main.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.navigation.SplashScreen
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.profile.main.presentation.component.ProfileButtons
import com.mycheva.app.profile.main.presentation.component.ProfileDetails
import com.mycheva.app.profile.main.presentation.component.ProfileImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    state: ProfileScreenState,
    onEvent: (ProfileScreenEvent) -> Unit,
    onNavigate: (destination: Any) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank())
            onEvent(ProfileScreenEvent.OnGetProfile(state.token, state.id))
    }

    LaunchedEffect(key1 = state.isLogoutSuccess) {
        if (state.isLogoutSuccess){
            delay(1000)
            onNavigate(SplashScreen)
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()){
            delay(2000)
            onEvent(ProfileScreenEvent.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isError,
        content = {
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = "Profile",
                                color = Primary50,
                                fontFamily = poppinsFamily
                            )
                        },
                        actions = {
                            IconButton(
                                onClick = {
                                    onEvent(ProfileScreenEvent.OnLogout(state.token))
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                                    contentDescription = "logout",
                                    tint = Primary50
                                )
                            }
                        },
                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                            containerColor = Primary500
                        ),
                        scrollBehavior = scrollBehavior
                    )
                }
            ) {
                LazyColumn(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    contentPadding = it
                ) {
                    item {
                        Box(
                            modifier = Modifier.height(156.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Primary500)
                                        .weight(1f)
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                )
                            }
                            ProfileImage(
                                imageUrl = state.imageUrl,
                                onEditImage = {
                                    onEvent(ProfileScreenEvent.OnEditProfile)
                                }
                            )
                        }
                    }
                    item {
                        ProfileDetails(
                            name = state.name,
                            username = state.username,
                            nim = state.nim,
                            faculty = state.faculty,
                            major = state.major,
                            division = state.division
                        )
                    }
                    item {
                        ProfileButtons(
                            onNavigate = onNavigate
                        )
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun ProfileScreenPrev() {
    ProfileScreen(
        state = ProfileScreenState(),
        onEvent = { },
        onNavigate = { }
    )
}
