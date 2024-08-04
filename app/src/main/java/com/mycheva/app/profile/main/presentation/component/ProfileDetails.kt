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
import com.mycheva.app.profile.main.data.User


@Composable
fun ProfileDetails(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = MaterialTheme.shapes.medium),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProfileDetailRow(label = "Username", value = user.username)
        ProfileDetailRow(label = "Nama", value = user.name)
        ProfileDetailRow(label = "NIM", value = user.nim)
        ProfileDetailRow(label = "Fakultas", value = user.faculty)
        ProfileDetailRow(label = "Jurusan", value = user.major)
        ProfileDetailRow(label = "Divisi", value = user.division)
    }
}
