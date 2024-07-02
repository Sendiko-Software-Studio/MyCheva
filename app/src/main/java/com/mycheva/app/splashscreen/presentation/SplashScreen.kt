package com.mycheva.app.splashscreen.presentation

import android.annotation.SuppressLint
import android.window.SplashScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    onNavigate: (String) -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(1000)
        onNavigate("")
    }

    Scaffold(
        containerColor = Primary500
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.chevalier_logo),
                    contentDescription = "Chevalier Lab",
                    modifier = Modifier.size(128.dp)
                )
                Column {
                    Text(
                        text = "Chevalier Lab",
                        fontFamily = poppinsFamily,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Software Artist Studio",
                        fontFamily = poppinsFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun SplashScreenPrev() {
    SplashScreen {

    }
}