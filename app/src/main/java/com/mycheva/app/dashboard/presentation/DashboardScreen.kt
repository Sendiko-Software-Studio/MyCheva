package com.mycheva.app.dashboard.presentation

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Announcement
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.rounded.Map
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.navigation.AttendanceScreen
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import com.mycheva.app.dashboard.presentation.component.MeetingCard
import com.mycheva.app.dashboard.presentation.component.MenuCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onNavigate: (route: Any) -> Unit
) {
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                scrollBehavior = topAppBarScrollBehavior,
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.chevalier_logo),
                            contentDescription = "logo",
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = "MyCheva",
                            fontFamily = poppinsFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Announcement,
                            contentDescription = "Announcement",
                            tint = Color.Black
                        )
                    }
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = Primary500,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile"
                        )
                    }
                }
            )
        },
    ) {
        LazyColumn(
            modifier = Modifier
                .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
            contentPadding = PaddingValues(
                top = it.calculateTopPadding(),
                bottom = it.calculateBottomPadding(),
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        text = "Selamat pagi, ",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        fontFamily = poppinsFamily,
                    )
                    Text(
                        text = "Sendiko",
                        fontSize = 24.sp,
                        fontFamily = poppinsFamily,
                    )
                }
            }
            item {
                Text(
                    text = "Acara minggu ini",
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFamily
                )
                Spacer(modifier = Modifier.height(8.dp))
                MeetingCard(
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "Menu",
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFamily
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    MenuCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Rounded.QrCode,
                        label = "Presensi QR",
                        onClick = {
                            onNavigate(AttendanceScreen)
                        }
                    )
                    MenuCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Rounded.Map,
                        label = "Lihat Roadmap",
                        onClick = { }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun DashboardScreenPrev() {
    DashboardScreen(
        onNavigate = {

        }
    )
}