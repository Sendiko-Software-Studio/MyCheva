package com.mycheva.app.profile.main.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mycheva.app.core.ui.theme.Neutral400


@Composable
fun ProfileDetails(
    username: String = "",
    email: String = "",
    name: String = "",
    nim: String = "",
    faculty: String = "",
    major: String = "",
    division: String = "",
    isLoading: Boolean,
) {
    AnimatedContent(targetState = isLoading, label = "") { isLoading ->
        if (!isLoading) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileDetailRow(label = "Nama", value = name)
                ProfileDetailRow(label = "NIM", value = nim)
                ProfileDetailRow(label = "Email", value = email)
                ProfileDetailRow(label = "Fakultas", value = faculty)
                ProfileDetailRow(label = "Jurusan", value = major)
                ProfileDetailRow(label = "Divisi", value = division)
            }
        } else {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                repeat(5) {
                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .width(200.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                Neutral400
                            )
                    )
                }
            }
        }
    }
}
