package com.mycheva.app.roadmap.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.ui.theme.Neutral900
import com.mycheva.app.core.ui.theme.Primary300
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.roadmap.data.RoadmapsItem

@Composable
fun RoadMapCard(
    modifier: Modifier = Modifier,
    roadMapItem: RoadmapsItem
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Primary300,
            contentColor = Neutral900
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = roadMapItem.title,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = roadMapItem.desc,
                fontFamily = poppinsFamily,
            )
        }
    }
}

@Preview
@Composable
private fun RoadMapCardPrev() {
    Surface {
        val data = RoadmapsItem(
            createdAt = "",
            updatedAt = "",
            id = 0,
            divisionId = 0,
            title = "Lorem Ipsum",
            desc = "Lorem ipsum dolot sir amet adios forosum el kontole"
        )
        RoadMapCard(roadMapItem = data, modifier = Modifier.padding(16.dp))
    }
}