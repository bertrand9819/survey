package com.example.steppeview.Utilis

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomFormContent(
    title: String? = null,
    image: Painter? = null,
    buttonText: String,
    onButtonClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
    ) {
        if (title != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        if (image != null) {
            Image(
                painter = image,
                contentDescription = "Custom Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        content()
        Button(
            onClick = onButtonClicked,
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor =
            Color(android.graphics.Color.parseColor("#179138"))
            ),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(text = buttonText)
        }
    }
}