package com.mycheva.app.schedule.detail.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.components.LargeTopBar
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.formatDateString
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.Primary300
import com.mycheva.app.core.ui.theme.poppinsFamily

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScheduleScreen(
    state: DetailScheduleState,
    eventId: String,
    onEvent: (DetailScheduleEvent) -> Unit,
    onNavigateBack: () -> Unit
) {

    LaunchedEffect(key1 = eventId, key2 = state.token) {
        if (eventId.isNotBlank() && state.token.isNotBlank()) {
            onEvent(DetailScheduleEvent.OnLoadSchedule(state.token, eventId))
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestFailed
    ) {
        Scaffold(
            topBar = {
                LargeTopBar(
                    title = state.eventsItem?.name ?: "",
                    navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                    navigationAction = { onNavigateBack() }
                )
            },
            content = { paddingValues ->
                val padding = PaddingValues(
                    top = paddingValues.calculateTopPadding() + 16.dp,
                    end = 16.dp,
                    start = 16.dp
                )
                LazyColumn(
                    contentPadding = padding,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        AnimatedVisibility(
                            visible = state.eventsItem != null,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Row(
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                DateSection(
                                    modifier = Modifier.weight(1f),
                                    date = state.eventsItem!!.date.substring(0, 10),
                                )
                                Spacer(modifier = Modifier.width(16.dp))
                                LocationSection(
                                    modifier = Modifier.weight(1f),
                                    type = state.eventsItem!!.type.uppercase(),
                                )
                            }
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = state.eventsItem != null,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            Row(
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                TimeSection(
                                    modifier = Modifier.fillMaxWidth(1/2f),
                                    time = state.eventsItem!!.time,
                                )
                            }
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = state.eventsItem != null,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            DescriptionSection(
                                description = state.eventsItem!!.desc
                            )
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = state.eventsItem != null,
                            enter = fadeIn(),
                            exit = fadeOut()
                        ) {
                            DetailSection(
                                details = state.eventsItem!!.details,
                                type = state.eventsItem!!.type
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun DescriptionSection(
    description: String,
) {
    Column {
        Text(
            text = "Tentang",
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily
        )
        Text(
            text = description,
            fontSize = 16.sp,
            fontFamily = poppinsFamily
        )
    }
}

@Composable
fun DetailSection(
    details: String,
    type: String
) {
    val uriHandler = LocalUriHandler.current
    Column {
        Text(
            text = "Detail lainnya",
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily
        )
        Text(
            text = details,
            fontSize = 16.sp,
            fontFamily = poppinsFamily,
            modifier = Modifier.clickable {
                if (type == "online")
                    uriHandler.openUri(details)
            }
        )
    }
}

@Composable
fun LocationSection(
    type: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = Primary300
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Pelaksanaan",
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                color = Neutral900,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = type,
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                color = Neutral900
            )
        }
    }
}

@Composable
fun DateSection(
    modifier: Modifier = Modifier,
    date: String,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = Primary300
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Hari/Tanggal",
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                color = Neutral900,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = formatDateString(date),
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                color = Neutral900
            )
        }
    }
}

@Composable
fun TimeSection(
    modifier: Modifier = Modifier,
    time: String,
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        color = Primary300
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = "Waktu",
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                color = Neutral900,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = time,
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
                color = Neutral900
            )
        }
    }
}

@Preview
@Composable
private fun DetailScheduleScreenPrev() {
    DetailScheduleScreen(
        state = DetailScheduleState(),
        eventId = "0",
        onEvent = {}
    ) {

    }
}