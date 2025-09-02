package com.mycheva.app.profile.main.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.navigation.DashboardScreen
import com.mycheva.app.core.navigation.EditPasswordScreen
import com.mycheva.app.core.navigation.EditUsernameScreen
import com.mycheva.app.core.navigation.SplashScreen
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Error
import com.mycheva.app.core.ui.theme.Neutral400
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.profile.main.presentation.component.PasswordSheet
import com.mycheva.app.profile.main.presentation.component.ProfileButtons
import com.mycheva.app.profile.main.presentation.component.ProfileDetails
import com.mycheva.app.profile.main.presentation.component.ProfileImage
import com.mycheva.app.profile.main.presentation.component.UsernameSheet
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
    val context = LocalContext.current

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

    LaunchedEffect(key1 = state.isChangingPasswordSuccess) {
        if (state.isChangingPasswordSuccess) {
            delay(1000)
            onEvent(ProfileEvent.OnClearState)
        }
    }

    LaunchedEffect(key1 = state.isEditUsernameSuccess) {
        if (state.isEditUsernameSuccess) {
            delay(1000)
            onEvent(ProfileEvent.OnClearState)
            onEvent(ProfileEvent.OnGetProfile(state.token, state.id))
        }
    }

    LaunchedEffect(key1 = state.notificationMessage, key2 = state.isPasswordNotMatch) {
        if (state.notificationMessage.asString(context).isNotBlank() && !state.isPasswordNotMatch) {
            delay(2000)
            onEvent(ProfileEvent.OnClearState)
        }
    }

    LaunchedEffect(key1 = state.isError) {
        if (state.isError) {
            delay(2000)
            onEvent(ProfileEvent.OnClearMessage)
        }
    }

    NotificationBox(
        message = if (state.isPasswordNotMatch) "" else state.notificationMessage.asString(),
        isLoading = false,
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
                        AnimatedContent(targetState = state.isLoading, label = "") { isLoading ->
                            if (!isLoading) {
                                ProfileImage(
                                    imageUrl = state.imageUrl,
                                    onEditImage = {

                                    }
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(76.dp)
                                        .clip(CircleShape)
                                        .background(Neutral400)
                                )
                            }
                        }
                        AnimatedContent(targetState = state.isLoading, label = "") { isLoading ->
                            if (!isLoading) {
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
                            } else {
                                Column(
                                    verticalArrangement = Arrangement.SpaceEvenly
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(48.dp)
                                            .clip(
                                                RoundedCornerShape(4.dp)
                                            )
                                            .background(Neutral400)
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .height(20.dp)
                                            .width(128.dp)
                                            .clip(
                                                RoundedCornerShape(4.dp)
                                            )
                                            .background(Neutral400)
                                    )
                                }
                            }
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
                                    isLoading = state.isLoading,
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
                                ProfileButtons(
                                    onNavigate = {
                                        if (it is EditUsernameScreen) {
                                            onEvent(ProfileEvent.OnUsernameSheetToggle(!state.isEditingUsername))
                                        }
                                        if (it is EditPasswordScreen) {
                                            onEvent(ProfileEvent.OnPasswordSheetToggle(!state.isChangingPassword))
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
                if (state.isEditingUsername) {
                    UsernameSheet(
                        onChange = {
                            onEvent(ProfileEvent.OnUsernameEdit(it))
                        },
                        onDismiss = {
                            onEvent(ProfileEvent.OnUsernameSheetToggle(false))
                        },
                        isLoading = state.isLoadingUsername,
                        isSuccess = state.isEditUsernameSuccess,
                        isError = state.isError
                    )
                }
                if (state.isChangingPassword) {
                    PasswordSheet(
                        onChange = { pass, newPass ->
                            onEvent(
                                ProfileEvent.OnPasswordEdit(
                                    password = newPass,
                                    oldPassword = pass
                                )
                            )
                        },
                        onDismiss = {
                            onEvent(ProfileEvent.OnPasswordSheetToggle(false))
                        },
                        isLoading = state.isLoadingPassword,
                        isSuccess = state.isChangingPasswordSuccess,
                        isError = state.isPasswordNotMatch
                    )
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
