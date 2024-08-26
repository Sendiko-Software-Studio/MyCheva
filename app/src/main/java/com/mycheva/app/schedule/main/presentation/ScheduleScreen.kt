package com.mycheva.app.schedule.main.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Neutral200
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.schedule.main.presentation.component.EventCard
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun ScheduleScreen(
    state: ScheduleScreenState,
    onEvent: (ScheduleScreenEvent) -> Unit,
    onNavigate: (eventId: String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank())
            onEvent(ScheduleScreenEvent.OnLoadSchedule(state.token))
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(ScheduleScreenEvent.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
        isErrorNotification = state.isRequestFailed
    ) {
        Scaffold(
            topBar = {
                MediumTopAppBar(
                    title = {
                        Text(
                            text = "Jadwal",
                            fontFamily = poppinsFamily,
                        )
                    },
                    scrollBehavior = scrollBehavior,
                    colors = TopAppBarDefaults.largeTopAppBarColors(
                        scrolledContainerColor = Neutral200
                    )
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    contentPadding = paddingValues,
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    items(state.events.entries.toList()) { (date, events) ->
                        Text(
                            text = date.toString(),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = poppinsFamily,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        events.forEach { event ->
                            EventCard(
                                eventsItem = event,
                                onClick = { onNavigate(event.id.toString()) },
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }

                }

            }
        )
    }
}
