package com.mycheva.app.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.theme.Neutral400

@Composable
fun CardSkeleton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(128.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(Neutral400)
    )
}