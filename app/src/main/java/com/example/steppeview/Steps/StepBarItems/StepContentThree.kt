package com.example.steppeview.Steps.StepBarItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.steppeview.Utilis.EmojiList

@Composable
fun StepThreeContent(onNextStep: () -> Unit, totalSteps: Int) {
    var selectedEmoji by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Quelle est votre satisfaction  globale \n" +
                        "         à l'égard de nos service ?",
                fontStyle = FontStyle.Normal
            )
            Spacer(modifier = Modifier.width(30.dp))
        }

        EmojiList(onEmojiSelected = { selectedEmoji = it })

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (selectedEmoji.isNotEmpty()) {
                        onNextStep()
                    }
                },
                enabled = selectedEmoji.isNotEmpty(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(65.dp)
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(containerColor =
                Color(android.graphics.Color.parseColor("#179138"))
                ),
                elevation = ButtonDefaults.elevatedButtonElevation()
            ) {
                Text(text = "Next")
            }
        }
    }
}