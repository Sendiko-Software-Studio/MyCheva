package com.mycheva.app.profile.edit_pass.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.navigation.ProfileScreen
import com.mycheva.app.core.ui.components.LargeTopBar
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EditPasswordScreen(
    state: EditPasswordScreenState,
    onEvent: (EditPasswordScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()){
            delay(2000)
            onEvent(EditPasswordScreenEvent.OnClearState)
        }
    }

    LaunchedEffect(key1 = state.isRequestSuccess) {
        if (state.isRequestSuccess){
            delay(2000)
            onNavigate(ProfileScreen)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestError
    ) {
        Scaffold(
            topBar = {
                LargeTopBar(
                    title = "Ubah Password",
                    navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                    navigationAction = {
                        onNavigate(null)
                    },
                    actionIcon = Icons.Rounded.Check,
                    actionAction = {
                        onEvent(EditPasswordScreenEvent.OnSave(state.token, state.userId, state.newPassText))
                    }
                )
            }
        ) { it ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                CustomTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = "Password Lama",
                    value = state.oldPassText,
                    onValueChange = {
                        onEvent(EditPasswordScreenEvent.OnOldPassChanged(it))
                    },
                    onClearClick = { onEvent(EditPasswordScreenEvent.OnOldPassCleared) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Lock, contentDescription = "password")
                    }
                )
                CustomTextField(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    label = "Password Baru",
                    value = state.newPassText,
                    onValueChange = {
                        onEvent(EditPasswordScreenEvent.OnNewPassChanged(it))
                    },
                    onClearClick = { onEvent(EditPasswordScreenEvent.OnNewPassCleared) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Rounded.Lock, contentDescription = "password")
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun UbahPasswordPrev() {
    EditPasswordScreen(
        state = EditPasswordScreenState(),
        onEvent = { },
        onNavigate = { }
    )
}