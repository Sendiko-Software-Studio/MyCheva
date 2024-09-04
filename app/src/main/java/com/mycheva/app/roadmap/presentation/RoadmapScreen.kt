package com.mycheva.app.roadmap.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.components.NotificationBox
import com.mycheva.app.roadmap.presentation.component.RoadMapCard
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.RoadMapScreen(
    state: RoadMapState,
    onEvent: (RoadMapEvent) -> Unit,
    animationVisibility: AnimatedVisibilityScope,
    onNavigateBack: () -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(key1 = state.token) {
        if (state.token.isNotBlank())
            onEvent(RoadMapEvent.OnLoadData(token = state.token, divisionId = ""))
    }

    LaunchedEffect(key1 = state.notificationMessage) {
        if (state.notificationMessage.isNotBlank()) {
            delay(2000)
            onEvent(RoadMapEvent.OnClearState)
        }
    }

    NotificationBox(
        message = state.notificationMessage,
        isLoading = state.isLoading,
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
                    title = { Text(text = "Road Map") },
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
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
                ) {
                    items(state.roadMaps) { roadmap ->
                        RoadMapCard(roadMapItem = roadmap)
                        if (state.roadMaps.last() != roadmap){
                            Icon(
                                imageVector = Icons.Rounded.ArrowDownward,
                                contentDescription = "next",
                                modifier = Modifier.size(32.dp)
                            )
                        } else {
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                    }
                }
            }
        )
    }
}
