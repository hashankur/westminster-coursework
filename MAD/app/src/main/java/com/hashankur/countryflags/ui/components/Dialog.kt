package com.hashankur.countryflags.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun CheckAnswerDialog(
    onDismissRequest: () -> Unit,
    dialogStatus: Boolean,
    country: String,
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        // Draw a rectangle shape with rounded corners inside the dialog
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = if (dialogStatus) "CORRECT!" else "WRONG!",
                    modifier = Modifier.padding(16.dp),
                    color = if (dialogStatus) Color(0xFF1C6B50) else Color(0xFFFF5A39),
                    fontWeight = FontWeight.Bold,
                    fontSize = 35.sp
                )
                Text(
                    text = country,
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally),
                    color = Color(0xFF4285F4),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
