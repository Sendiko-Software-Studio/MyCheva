package com.mycheva.app.schedule.detail.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.components.CenteredAppBar
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.schedule.main.presentation.component.EventCard

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
                CenteredAppBar(
                    title = "Detail Jadwal",
                    navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                    navigationAction = { onNavigateBack() }
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    contentPadding = paddingValues,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        AnimatedVisibility(
                            visible = state.eventsItem != null,
                            enter = slideInHorizontally(),
                            exit = slideOutHorizontally()

                        ) {
                            EventCard(
                                modifier = Modifier.padding(16.dp),
                                onClick = { },
                                eventsItem = state.eventsItem!!
                            )
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = state.eventsItem != null,
                            enter = slideInHorizontally(),
                            exit = slideOutHorizontally()
                        ) {
                            DescriptionSection(
                                description = state.eventsItem!!.desc
                            )
                        }
                    }
                    item {
                        AnimatedVisibility(
                            visible = state.eventsItem != null,
                            enter = slideInHorizontally(),
                            exit = slideOutHorizontally()
                        ) {
                            LocationSection(
                                type = state.eventsItem!!.type,
                                location = state.eventsItem.details
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Deskripsi",
            fontSize = 16.sp,
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
fun LocationSection(
    type: String,
    location: String
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = "Lokasi",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = poppinsFamily
        )
        Column {
            Text(
                text = type,
                fontSize = 16.sp,
                fontFamily = poppinsFamily
            )
            Text(
                text = location,
                fontSize = 16.sp,
                fontFamily = poppinsFamily
            )
        }
    }
}