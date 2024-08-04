package com.mycheva.app.profile.main.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Button(
            onClick = { onNavigate(EditUsernameScreen) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Primary500),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Ubah username",
                fontSize = 16.sp,
                fontWeight = Bold,
                fontFamily = poppinsFamily
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onNavigate(EditPasswordScreen) },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Primary500),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Ubah password",
                fontSize = 16.sp,
                fontWeight = Bold,
                fontFamily = poppinsFamily,
            )
        }
    }
}