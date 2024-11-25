package com.mycheva.app.meeting.main.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.ui.components.CardSkeleton
import com.mycheva.app.core.ui.components.CustomTextField
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.components.TextFieldType
import com.mycheva.app.core.ui.components.formatDateString
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.core.ui.components.MeetingCard
import com.mycheva.app.meeting.main.presentation.component.CalendarSkeleton
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MeetingsScreen(
    state: MeetingsState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onEvent: (MeetingsAction) -> Unit,
    onNavigate: (eventId: String) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank())
            onEvent(MeetingsAction.OnLoadSchedule(state.token))
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(MeetingsAction.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = false,
        isErrorNotification = state.isRequestFailed
    ) {
        Scaffold(
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "schedule"),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            topBar = {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { onNavigate("") }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    },
                    title = { Text(text = "Jadwal", fontFamily = poppinsFamily) },
                    scrollBehavior = scrollBehavior
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    contentPadding = paddingValues,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    item {
                        CustomTextField(
                            value = state.searchText,
                            onValueChange = { onEvent(MeetingsAction.OnSearchTextChanged(it)) },
                            onClearClick = { onEvent(MeetingsAction.OnClearFilter) },
                            type = TextFieldType.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp),
                            label = "Cari..",
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "cari"
                                )
                            }
                        )
                    }
                    item {
                        AnimatedVisibility(!state.isLoading && state.events.isEmpty()) {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    modifier = Modifier.size(256.dp),
                                    painter = painterResource(R.drawable.not_found),
                                    contentDescription = "Konten tidak ditemukan."
                                )
                            }
                        }
                    }
                    item {
                        LazyRow(
                            contentPadding = PaddingValues(horizontal = 16.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.padding(bottom = 16.dp)
                        ) {
                            val dates = state.events.distinctBy {
                                it.date
                            }
                            item {
                                (1..5).forEach {
                                    AnimatedVisibility(
                                        visible = state.isLoading && state.events.isEmpty(),
                                        enter = expandHorizontally(),
                                        exit = shrinkHorizontally()
                                    ) {
                                        CalendarSkeleton(modifier = Modifier.padding(end = 16.dp))
                                    }
                                }
                            }
                            items(dates) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = Primary500
                                ) {
                                    Column(
                                        modifier = Modifier.padding(8.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = formatDateString(it.date.toString().substring(0, 10)).substring(0, 2),
                                            fontWeight = FontWeight.Bold,
                                            fontFamily = poppinsFamily,
                                            color = Neutral50
                                        )
                                        Text(
                                            text = formatDateString(it.date.toString().substring(0, 10)).substring(2, 6),
                                            fontWeight = FontWeight.Normal,
                                            fontFamily = poppinsFamily,
                                            color = Neutral50,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                    item {
                        repeat(5) {
                            AnimatedVisibility(
                                visible = state.isLoading && state.events.isEmpty(),
                                enter = expandVertically(),
                                exit = shrinkVertically()
                            ) {
                                CardSkeleton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                )
                            }
                        }
                    }
                    items(state.events) { event ->
                        MeetingCard(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                            eventsItem = event,
                            onClick = { eventId ->
                                onNavigate(eventId)
                            }
                        )
                    }

                }

            }
        )
    }
}
