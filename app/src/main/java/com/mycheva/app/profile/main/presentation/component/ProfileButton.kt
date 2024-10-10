package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mycheva.app.core.navigation.EditPasswordScreen
import com.mycheva.app.core.navigation.EditUsernameScreen
import com.mycheva.app.core.ui.theme.Primary500
import com.mycheva.app.core.ui.theme.poppinsFamily

@Composable
fun ProfileButtons(
    onNavigate: (destination: Any) -> Unit,
) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedButton(
            onClick = { onNavigate(EditUsernameScreen) },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Primary500
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(width = 1.dp, color = Primary500)
        ) {
            Text(
                text = "Ubah username",
                fontSize = 14.sp,
                fontFamily = poppinsFamily
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        OutlinedButton(
            onClick = { onNavigate(EditPasswordScreen) },
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Primary500
            ),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(width = 1.dp, color = Primary500)
        ) {
            Text(
                text = "Ubah password",
                fontSize = 14.sp,
                fontFamily = poppinsFamily,
            )
        }
    }
}