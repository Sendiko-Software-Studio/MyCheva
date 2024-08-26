package com.mycheva.app.forum.add.presentation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.components.LargeTopBar
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.PlainTextField
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPostScreen(
    state: AddPostScreenState,
    onEvent: (AddPostScreenEvent) -> Unit,
    onNavigateBack: () -> Unit,
) {

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(AddPostScreenEvent.OnClearState)
        }
    }

    LaunchedEffect(key1 = state.isRequestSuccess) {
        if (state.isRequestSuccess) {
            delay(2000)
            onNavigateBack()
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
                    title = "Buat Postingan",
                    navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                    navigationAction = { onNavigateBack() }
                )
            },
            content = { paddingValues ->
                val padding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    start = 16.dp,
                    end = 16.dp
                )
                LazyColumn(
                    contentPadding = padding
                ) {
                    item {
                        PlainTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = state.postText,
                            placeholder = "Apa yang ingin ditanyakan?",
                            onValueChange = {
                                onEvent(AddPostScreenEvent.OnPostTextChanged(it))
                            }
                        )
                    }
                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Primary500
                                ),
                                modifier = Modifier
                                    .weight(1f),
                                onClick = {  },
                                content = {
                                    Text(
                                        text = "Unggah gambar",
                                        fontWeight = Bold,
                                        fontFamily = poppinsFamily
                                    )
                                },
                                shape = RoundedCornerShape(8.dp),
                                enabled = false
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Primary500
                                ),
                                modifier = Modifier
                                    .weight(1f),
                                onClick = { onEvent(AddPostScreenEvent.OnPost(state.token, state.userId, state.postText)) },
                                content = {
                                    Text(
                                        text = "Kirim",
                                        fontWeight = Bold,
                                        fontFamily = poppinsFamily
                                    )
                                },
                                shape = RoundedCornerShape(8.dp),
                            )
                        }
                    }
                }
            }
        )
    }
}
