package com.mycheva.app.schedule.main.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.schedule.main.presentation.component.Calendar
import com.mycheva.app.schedule.main.presentation.component.EventReminder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Jadwal",
                        fontFamily = poppinsFamily
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary500,
                    titleContentColor = Primary50
                )
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Calendar(paddingValues)
                HorizontalDivider(
                    color = Color.Gray,
                    thickness = 2.dp,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                EventReminder(paddingValues)
            }

        }
    )
}

@Preview
@Composable
private fun ScheduleScreenPrev() {
    ScheduleScreen()
}