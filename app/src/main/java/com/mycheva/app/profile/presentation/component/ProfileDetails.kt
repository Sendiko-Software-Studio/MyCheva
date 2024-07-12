package com.mycheva.app.profile.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ProfileDetails() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium)
    ) {
        ProfileDetailRow(label = "Username", value = "Kapal Laut")
        ProfileDetailRow(label = "Nama", value = "Budiono Siregar")
        ProfileDetailRow(label = "NIM", value = "1234567890")
        ProfileDetailRow(label = "Fakultas", value = "Kedokteran")
        ProfileDetailRow(label = "Jurusan", value = "Tata boga")
        ProfileDetailRow(label = "Divisi", value = "UI/UX")
    }
}
