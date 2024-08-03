package com.mycheva.app.roadmap.presentation.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun RoadmapItem(title: String, description: String, isLeft: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isLeft) Arrangement.Start else Arrangement.End
    ) {
        if (isLeft) {
            Column(
                modifier = Modifier
                    .width(150.dp)
                    .padding(start = 8.dp)
            ) {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = description)
            }
            Spacer(modifier = Modifier.width(20.dp))
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Canvas(
                modifier = Modifier.size(24.dp)
            ) {
                drawCircle(color = Color(0xFF454B60), radius = size.minDimension / 2)
            }
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(80.dp)
                    .background(Color.Black)
            )
        }

        if (!isLeft) {
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier
                    .width(150.dp)
                    .padding(start = 8.dp)
            ) {
                Text(text = title, fontWeight = FontWeight.Bold)
                Text(text = description)
            }
        }
    }
}
