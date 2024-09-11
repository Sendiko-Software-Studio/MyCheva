package com.mycheva.app.profile.main.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.navigation.DashboardScreen
import com.mycheva.app.core.navigation.SplashScreen
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Error
import com.mycheva.app.core.ui.theme.Neutral50
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
    state: ProfileState,
    onEvent: (ProfileEvent) -> Unit,
    onNavigate: (destination: Any) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank())
            onEvent(ProfileEvent.OnGetProfile(state.token, state.id))
    }

    LaunchedEffect(key1 = state.isLogoutSuccess) {
        if (state.isLogoutSuccess) {
            delay(1000)
            onNavigate(SplashScreen)
        }
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(ProfileEvent.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isError,
        content = {
            Scaffold(
                containerColor = Primary500,
                topBar = {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Primary500,
                            titleContentColor = Neutral50,
                            navigationIconContentColor = Neutral50
                        ),
                        title = { Text(text = "Profile", fontFamily = poppinsFamily) },
                        navigationIcon = {
                            IconButton(onClick = { onNavigate(DashboardScreen) }) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                    contentDescription = "back"
                                )
                            }
                        },
                        actions = {
                            Button(
                                onClick = {
                                    onEvent(ProfileEvent.OnLogout(state.token))
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Error,
                                    contentColor = Neutral50
                                )
                            ) {
                                Text(text = "Logout", fontFamily = poppinsFamily)
                            }
                        }
                    )
                }
            ) { paddingValues ->
                val padding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
                Column(
                    modifier = Modifier
                        .padding(top = paddingValues.calculateTopPadding())
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.padding(horizontal = 16.dp)
                    ) {
                        ProfileImage(
                            imageUrl = state.imageUrl,
                            onEditImage = {

                            }
                        )
                        Column(
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Text(
                                text = state.username,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = poppinsFamily,
                                color = Neutral50
                            )
                            Text(
                                text = state.division,
                                fontFamily = poppinsFamily,
                                color = Neutral50
                            )
                        }
                    }
                    Surface(
                        modifier = Modifier
                            .background(Primary500)
                            .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                            .fillMaxSize()
                    ) {
                        LazyColumn(
                            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            contentPadding = PaddingValues(16.dp)
                        ) {
                            item {
                                ProfileDetails(
                                    username = state.username,
                                    email = state.email,
                                    name = state.name,
                                    nim = state.nim,
                                    faculty = state.faculty,
                                    major = state.major,
                                    division = state.division
                                )
                            }
                            item {
                                ProfileButtons(onNavigate = { onNavigate(it) })
                            }
                        }
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
        state = ProfileState(),
        onEvent = { },
        onNavigate = { }
    )
}
