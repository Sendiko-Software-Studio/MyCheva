package com.mycheva.app.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Jadwal") }
            )
        },
        content = { paddingValues ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
            ) {
                Calendar(paddingValues)
                Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.padding(vertical = 8.dp))
                EventReminder(paddingValues)
            }

        }
    )
}

@Composable
fun Calendar(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = "Juli",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min").forEach {
                Text(text = it, fontSize = 16.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf("1", "2", "3", "4", "5", "6", "7").forEachIndexed { index, day ->
                Column(
                    modifier = Modifier
                        .width(40.dp)
                        .padding(vertical = 4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = day, fontSize = 16.sp)
                    Spacer(
                        modifier = Modifier
                            .height(2.dp)
                            .fillMaxWidth()
                            .background(if (index == 2) Color.Blue else Color.Transparent)
                    )
                }
            }
        }
    }
}


@Composable
fun EventReminder(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)

    ) {
        Text(
            text = "Jul 3",
            fontSize = 18.sp,
            fontWeight = FontWeight.Normal
        )
        Spacer(modifier = Modifier
            .height(8.dp)
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFE0F7FA),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(16.dp)
        ) {
            Text(
                text = "Study group ke-3",
                fontSize = 16.sp
            )
            Spacer(modifier = Modifier
                .height(4.dp)
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    painter = painterResource(id = R.drawable.jam_icon),
                    contentDescription = "Logo back",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "18.30 - 19.30",
                    fontSize = 16.sp
                )
            }
        }
    }
}