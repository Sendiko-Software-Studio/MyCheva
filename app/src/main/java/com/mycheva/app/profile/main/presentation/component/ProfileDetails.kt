package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun ProfileDetails(
    username: String = "",
    name: String = "",
    nim: String = "",
    faculty: String = "",
    major: String = "",
    division: String = "",
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfileDetailRow(label = "Username", value = username)
        ProfileDetailRow(label = "Nama", value = name)
        ProfileDetailRow(label = "NIM", value = nim)
        ProfileDetailRow(label = "Fakultas", value = faculty)
        ProfileDetailRow(label = "Jurusan", value = major)
        ProfileDetailRow(label = "Divisi", value = division)
    }
}
