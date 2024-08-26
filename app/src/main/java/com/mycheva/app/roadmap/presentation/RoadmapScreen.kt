package com.mycheva.app.roadmap.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mycheva.app.core.ui.components.LargeTopBar
import com.mycheva.app.roadmap.presentation.component.RoadmapContent

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.RoadmapScreen(
    onNavigateBack: () -> Unit,
    animatedContentScope: AnimatedContentScope
) {
    Scaffold(
        modifier = Modifier
            .sharedBounds(
                sharedContentState = rememberSharedContentState(key = "roadmap"),
                animatedVisibilityScope = animatedContentScope
            ),
        topBar = {
            LargeTopBar(
                title = "RoadMap",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = { onNavigateBack() },
            )
        },
        content = { paddingValues ->
            RoadmapContent(paddingValues)
        }
    )
}

