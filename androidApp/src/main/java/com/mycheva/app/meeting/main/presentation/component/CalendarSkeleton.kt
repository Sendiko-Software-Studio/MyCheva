package com.mycheva.app.meeting.main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.theme.Neutral400

@Composable
fun CalendarSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(48.dp)
            .height(64.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Neutral400)
    )
}