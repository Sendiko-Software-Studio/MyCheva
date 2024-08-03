package com.mycheva.app.roadmap.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.roadmap.data.Content
import com.mycheva.app.roadmap.domain.next
import com.mycheva.app.roadmap.domain.previous

@Composable
fun RoadmapContent(paddingValues: PaddingValues) {
    var currentContent by remember { mutableStateOf(Content.WebDevelopment) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 16.dp, vertical = 24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.left_roadmap_icon),
                contentDescription = "Left",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        currentContent = currentContent.previous()
                    }
            )
            Text(
                text = currentContent.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.right_roadmap_icon),
                contentDescription = "Right",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        currentContent = currentContent.next()
                    }
            )
        }
        RoadmapDivider()
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.align(Alignment.Center)) {
                currentContent.items.forEach { item ->
                    RoadmapItem(item.title, item.description, item.isLeft)
                }
            }
        }
    }
}