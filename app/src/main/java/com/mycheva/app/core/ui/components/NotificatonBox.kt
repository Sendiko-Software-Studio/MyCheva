package com.mycheva.app.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.mycheva.app.core.ui.theme.Error
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Primary700
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.sendiko.content_box_with_notification.ContentBoxWithNotification

@Composable
fun NotificationBox(
    modifier: Modifier = Modifier,
    message: String,
    isErrorNotification: Boolean = false,
    isLoading: Boolean = false,
    content: @Composable () -> Unit
) {
    ContentBoxWithNotification(
        message = message,
        isErrorNotification = isErrorNotification,
        isLoading = isLoading,
        containerColor = Primary700,
        contentColor = Neutral50,
        errorContainerColor = Error,
        contentErrorColor = Neutral50,
        loadingContainerColor = Primary700,
        loadingContentColor = Neutral50,
        textStyle = TextStyle(fontFamily = poppinsFamily),
        content = content
    )
}