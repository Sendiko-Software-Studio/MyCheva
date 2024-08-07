package com.mycheva.app.schedule.main.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.navigation.DetailSchedule
import com.mycheva.app.core.ui.theme.Primary50
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.schedule.main.presentation.component.Calendar
import com.mycheva.app.schedule.main.presentation.component.EventCard
import java.text.SimpleDateFormat
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ScheduleScreen(
    onNavigate: (destination: Any) -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val calendar = java.util.Calendar.getInstance()
    val dateFormat = SimpleDateFormat("MMMM dd", Locale.getDefault())
    val formattedDate = dateFormat.format(calendar.time)
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
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = formattedDate,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = poppinsFamily,
                    )
                    EventCard(
                        onClick = { onNavigate(DetailSchedule) },
                        modifier = Modifier.sharedElement(
                            state = rememberSharedContentState(key = "event_card"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                    )
                }
            }

        }
    )
}
