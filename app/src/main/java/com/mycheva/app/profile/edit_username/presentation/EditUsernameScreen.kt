package com.mycheva.app.profile.edit_username.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.components.CenteredAppBar
import com.mycheva.app.core.ui.components.CustomTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditUsernameScreen(
    state: EditUsernameScreenState,
    onEvent: (EditUsernameScreenEvent) -> Unit,
    onNavigate: (destination: Any?) -> Unit,
) {
    Scaffold(
        topBar = {
            CenteredAppBar(
                title = "Ubah Username",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = {
                    onNavigate(null)
                },
                actionIcon = Icons.Rounded.Check,
                actionAction = {

                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CustomTextField(
                modifier = Modifier.padding(16.dp),
                value = state.usernameText,
                onValueChange = { onEvent(EditUsernameScreenEvent.OnUsernameChanged(it)) },
                onClearClick = { onEvent(EditUsernameScreenEvent.OnUsernameCleared) },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "username"
                    )
                },
                label = "Username"
            )
        }
    }
}

@Preview
@Composable
private fun UbahUsernamePrev() {
    EditUsernameScreen(
        state = EditUsernameScreenState(),
        onEvent = { },
        onNavigate = { }
    )
}