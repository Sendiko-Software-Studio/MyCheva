package com.mycheva.app.roadmap

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R

@Composable
fun RoadmapScreen() {
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFA500))
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.back_logo),
                    contentDescription = "Logo back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(40.dp)
                )

                Text(
                    text = "Roadmap",
                    fontSize = 23.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier
                        .padding(horizontal = 110.dp, vertical = 5.dp)
                )
            }
        },
        content = { paddingValues ->
            RoadmapContent(paddingValues)
        }
    )
}

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

@Composable
fun RoadmapDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(2.dp)
                .height(30.dp)
                .background(Color.Transparent)
        )
    }
}

// Define Content as a data class
data class Content(val title: String, val items: List<RoadmapItemData>) {
    companion object {
        val WebDevelopment = Content(
            title = "Web Development",
            items = listOf(
                RoadmapItemData("Kegiatan 1", "Lorem ipsum dolor sit amet, Nullam vel orci.", true),
                RoadmapItemData("Kegiatan 2", "Lorem ipsum dolor sit amet, Nullam vel orci.", false),
                RoadmapItemData("Kegiatan 3", "Lorem ipsum dolor sit amet, Nullam vel orci.", true),
                RoadmapItemData("Kegiatan 4", "Lorem ipsum dolor sit amet, Nullam vel orci.", false)
            )
        )

        val UIUX = Content(
            title = "UI/UX",
            items = listOf(
                RoadmapItemData("Kegiatan 1", "Lorem ipsum dolor sit amet, vel orci.", true),
                RoadmapItemData("Kegiatan 2", "Lorem ipsum dolor sit amet, vel orci.", false),
                RoadmapItemData("Kegiatan 3", "Lorem ipsum dolor sit amet, vel orci.", true),
                RoadmapItemData("Kegiatan 4", "Lorem ipsum dolor sit amet, vel orci.", false)
            )
        )

        val Startup = Content(
            title = "Startup",
            items = listOf(
                RoadmapItemData("Kegiatan 1", "Lorem ipsum dolor sit amet, Nullam orci.", true),
                RoadmapItemData("Kegiatan 2", "Lorem ipsum dolor sit amet, Nullam orci.", false),
                RoadmapItemData("Kegiatan 3", "Lorem ipsum dolor sit amet, Nullam orci.", true),
                RoadmapItemData("Kegiatan 4", "Lorem ipsum dolor sit amet, Nullam orci.", false)
            )
        )
    }
}

data class RoadmapItemData(val title: String, val description: String, val isLeft: Boolean)

fun Content.next(): Content = when (this) {
    Content.WebDevelopment -> Content.UIUX
    Content.UIUX -> Content.Startup
    Content.Startup -> Content.WebDevelopment
    else -> Content.WebDevelopment
}

fun Content.previous(): Content = when (this) {
    Content.WebDevelopment -> Content.Startup
    Content.UIUX -> Content.WebDevelopment
    Content.Startup -> Content.UIUX
    else -> Content.WebDevelopment
}
