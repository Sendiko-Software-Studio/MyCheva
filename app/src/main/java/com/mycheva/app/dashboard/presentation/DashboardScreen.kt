package com.mycheva.app.dashboard.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Announcement
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Forum
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import com.mycheva.app.R
import com.mycheva.app.core.navigation.AnnouncementScreen
import com.mycheva.app.core.navigation.AttendanceScreen
import com.mycheva.app.core.navigation.DetailSchedule
import com.mycheva.app.core.navigation.ForumScreen
import com.mycheva.app.core.navigation.ProfileScreen
import com.mycheva.app.core.navigation.RoadmapScreen
import com.mycheva.app.core.navigation.ScheduleScreen
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.Neutral400
import com.mycheva.app.core.ui.theme.Neutral50
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.dashboard.presentation.component.EmptyMeetingCard
import com.mycheva.app.dashboard.presentation.component.MeetingCard
import com.mycheva.app.dashboard.presentation.component.MenuCard
import kotlinx.coroutines.delay
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.DashboardScreen(
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit,
    onNavigate: (route: Any) -> Unit,
    animatedContentScope: AnimatedContentScope
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val currentHour = LocalTime.now().hour

    val greeting: String = when (currentHour) {
        in 0..11 -> "Selamat pagi,"
        in 12..17 -> "Selamat siang,"
        else -> "Selamat malam,"
    }

    LaunchedEffect(key1 = state.userId) {
        if (state.userId.isNotBlank()) {
            delay(300)
            onEvent(DashboardEvent.GetUserData(state.token, state.userId))
        }
    }

    LaunchedEffect(key1 = state.divisionId) {
        if (state.divisionId.isNotBlank())
            onEvent(DashboardEvent.GetEventData(state.token))
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(DashboardEvent.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isErrorNotification = state.isRequestFailed
    ) {
        Scaffold(
            containerColor = Primary500,
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    scrollBehavior = topAppBarScrollBehavior,
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Primary500,
                        titleContentColor = Neutral50
                    ),
                    title = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.chevalier_logo),
                                contentDescription = "logo",
                                modifier = Modifier
                                    .size(36.dp)
                            )
                            Text(
                                text = "MyCheva",
                                fontFamily = poppinsFamily,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    },
                )
            },
        ) {
            val contentPadding = PaddingValues(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            )
            Column(
                Modifier.padding(top = it.calculateTopPadding())
            ) {
                Row(
                    modifier = Modifier
                        .padding(
                            start = 16.dp,
                            end = 16.dp,
                            bottom = 16.dp
                        )
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = greeting,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            fontFamily = poppinsFamily,
                            color = Neutral50
                        )
                        AnimatedContent(
                            targetState = state.isLoading,
                            label = "",
                            transitionSpec = {
                                fadeIn().togetherWith(fadeOut())
                            }
                        ) { isLoading ->
                            when (isLoading) {
                                false ->
                                    Text(
                                        text = state.name,
                                        fontSize = 24.sp,
                                        fontFamily = poppinsFamily,
                                        color = Neutral50
                                    )

                                true ->
                                    Box(
                                        modifier = Modifier
                                            .size(width = 136.dp, height = 32.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Neutral400)
                                    )
                            }
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconButton(onClick = { onNavigate(AnnouncementScreen) }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.Announcement,
                                contentDescription = "announcement",
                                tint = Neutral50
                            )
                        }
                        IconButton(
                            onClick = { onNavigate(ProfileScreen) },
                            colors = IconButtonDefaults.iconButtonColors(
                                containerColor = Neutral50,
                                contentColor = Neutral900
                            )
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Person,
                                contentDescription = "profile",
                            )
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
                        modifier = Modifier
                            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Text(
                                text = "Acara minggu ini",
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            AnimatedContent(
                                targetState = state.isLoading,
                                label = "",
                                transitionSpec = {
                                    fadeIn().togetherWith(fadeOut())
                                },
                            ) { isLoading ->
                                when (isLoading) {
                                    false -> {
                                        if (state.latestEvent != null) {
                                            MeetingCard(
                                                modifier = Modifier.fillMaxWidth(),
                                                eventsItem = state.latestEvent,
                                                onClick = { eventId ->
                                                    onNavigate(DetailSchedule(eventId))
                                                }
                                            )
                                        }
                                        if (state.latestEvent == null && !state.isLoading)
                                            EmptyMeetingCard(modifier = Modifier.fillMaxWidth())
                                    }

                                    true ->
                                        Box(
                                            modifier = Modifier
                                                .height(128.dp)
                                                .fillMaxWidth()
                                                .clip(RoundedCornerShape(4.dp))
                                                .background(Neutral400)
                                        )
                                }
                            }
                        }
                        item {
                            Text(
                                text = "Menu",
                                fontWeight = FontWeight.Bold,
                                fontFamily = poppinsFamily
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                MenuCard(
                                    modifier = Modifier
                                        .weight(1f)
                                        .sharedBounds(
                                            sharedContentState = rememberSharedContentState(key = "presensi"),
                                            animatedVisibilityScope = animatedContentScope
                                        ),
                                    icon = Icons.Rounded.QrCode,
                                    label = "Presensi QR",
                                    enabled = !state.isLoading,
                                    onClick = { onNavigate(AttendanceScreen) }
                                )
                                MenuCard(
                                    modifier = Modifier
                                        .weight(1f)
                                        .sharedBounds(
                                            sharedContentState = rememberSharedContentState(key = "roadmap"),
                                            animatedVisibilityScope = animatedContentScope
                                        ),
                                    icon = Icons.Rounded.Map,
                                    label = "Roadmap",
                                    enabled = !state.isLoading,
                                    onClick = { onNavigate(RoadmapScreen("0")) }
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                MenuCard(
                                    modifier = Modifier
                                        .weight(1f)
                                        .sharedBounds(
                                            sharedContentState = rememberSharedContentState(key = "schedule"),
                                            animatedVisibilityScope = animatedContentScope
                                        ),
                                    icon = Icons.Rounded.CalendarMonth,
                                    label = "Jadwal",
                                    enabled = !state.isLoading,
                                    onClick = {
                                        onNavigate(ScheduleScreen)
                                    }
                                )
                                MenuCard(
                                    modifier = Modifier
                                        .weight(1f)
                                        .sharedBounds(
                                            sharedContentState = rememberSharedContentState(key = "forum"),
                                            animatedVisibilityScope = animatedContentScope,
                                        ),
                                    icon = Icons.Rounded.Forum,
                                    label = "Forum",
                                    enabled = !state.isLoading,
                                    onClick = {
                                        onNavigate(ForumScreen)
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}