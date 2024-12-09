package com.mycheva.app.roadmap.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.mycheva.app.R
import com.mycheva.app.core.ui.components.CardSkeleton
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.roadmap.presentation.component.RoadMapCard
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.RoadMapScreen(
    state: RoadMapState,
    onEvent: (RoadMapEvent) -> Unit,
    animationVisibility: AnimatedVisibilityScope,
    onNavigateBack: () -> Unit,
    divisionId: String
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token, key2 = divisionId) {
        if (state.token.isNotBlank() && divisionId.isNotBlank())
            onEvent(RoadMapEvent.OnLoadData(token = state.token, divisionId = divisionId))
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(RoadMapEvent.OnClearState)
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
                    sharedContentState = rememberSharedContentState(key = "roadmap"),
                    animatedVisibilityScope = animationVisibility
                ),
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text(text = "Road Map", fontFamily = poppinsFamily) },
                    navigationIcon = {
                        IconButton(onClick = onNavigateBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            },
            content = { paddingValues ->
                LazyColumn(
                    contentPadding = PaddingValues(
                        top = paddingValues.calculateTopPadding() + 16.dp,
                        start = 16.dp,
                        end = 16.dp
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    item {
                        AnimatedVisibility(!state.isLoading && state.roadMaps.isEmpty()) {
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
                        (1..5).forEach {
                            AnimatedVisibility(
                                visible = state.isLoading && state.roadMaps.isEmpty(),
                                enter = expandVertically(),
                                exit = shrinkVertically()
                            ) {
                                CardSkeleton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 16.dp)
                                )
                            }
                        }
                    }
                    items(state.roadMaps) { roadmap ->
                        RoadMapCard(
                            roadMapItem = roadmap,
                            modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth(),
                        )
                        if (state.roadMaps.last() != roadmap) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowDownward,
                                contentDescription = "next",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        } else {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        )
    }
}
