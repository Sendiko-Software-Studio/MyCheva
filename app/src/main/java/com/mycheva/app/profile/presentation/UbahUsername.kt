package com.mycheva.app.profile.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.R
import com.mycheva.app.core.ui.theme.Primary500

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UbahUsernameScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Primary500,
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                title = {
                    Text(
                        text = "Ubah Username",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.Check, contentDescription = "Done")
                    }
                }
            )
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Text(
                    text = "Username",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.fillMaxWidth(),
                    //textAlign = TextAlign.Start
                )
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = "Kapal Laut",
                    onValueChange = { },
                    colors = TextFieldDefaults.colors(
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color.Black,
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent
                    ),
                    leadingIcon = {
                        Image(
                            modifier = Modifier
                                .size(24.dp),
                            painter = painterResource(id = R.drawable.username_logo),
                            contentDescription = "logo username"
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun UbahUsernamePrev() {
    UbahUsernameScreen()
}