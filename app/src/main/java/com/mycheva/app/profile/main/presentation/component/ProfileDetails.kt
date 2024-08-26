package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp


@Composable
fun ProfileDetails(
    username: String = "",
    email: String = "",
    name: String = "",
    nim: String = "",
    faculty: String = "",
    major: String = "",
    division: String = "",
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileDetailRow(label = "Nama", value = name)
        ProfileDetailRow(label = "Email", value = email)
        ProfileDetailRow(label = "Fakultas", value = faculty)
        ProfileDetailRow(label = "Jurusan", value = major)
        ProfileDetailRow(label = "Divisi", value = division)
    }
}
