package com.mycheva.app.roadmap.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.mycheva.app.core.ui.components.CenteredAppBar
import com.mycheva.app.roadmap.presentation.component.RoadmapContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RoadmapScreen(
    onNavigateBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenteredAppBar(
                title = "RoadMap",
                navigationIcon = Icons.AutoMirrored.Rounded.ArrowBack,
                navigationAction = { onNavigateBack() }
            )
        },
        content = { paddingValues ->
            RoadmapContent(paddingValues)
        }
    )
}

