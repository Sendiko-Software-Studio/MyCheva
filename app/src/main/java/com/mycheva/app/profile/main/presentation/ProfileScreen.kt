package com.mycheva.app.profile.main.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.navigation.SplashScreen
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Neutral200
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
                    MediumTopAppBar(
                        title = {
                            Text(
                                text = "Profile",
                                fontFamily = poppinsFamily,
                            )
                        },
                        scrollBehavior = scrollBehavior,
                        colors = TopAppBarDefaults.largeTopAppBarColors(
                            scrolledContainerColor = Neutral200
                        ),
                        actions = {
                            IconButton(
                                onClick = {
                                    onEvent(ProfileScreenEvent.OnLogout(state.token))
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.Logout,
                                    contentDescription = "logout",
                                )
                            }
                        },
                    )
                }
            ) {
                val padding = PaddingValues(
                    top = it.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = it.calculateBottomPadding()
                )
                LazyColumn(
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                    contentPadding = padding,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            ProfileImage(
                                imageUrl = state.imageUrl,
                                onEditImage = {
                                    onEvent(ProfileScreenEvent.OnEditProfile)
                                }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Column(
                                modifier = Modifier.height(76.dp),
                                verticalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Text(
                                    text = state.username,
                                    style = MaterialTheme.typography.headlineSmall,
                                    fontFamily = poppinsFamily
                                )
                                Text(
                                    text = state.nim,
                                    style = MaterialTheme.typography.bodyLarge,
                                    fontFamily = poppinsFamily
                                )
                            }
                        }
                    }
                    item {
                        ProfileDetails(
                            name = state.name,
                            email = state.email,
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
