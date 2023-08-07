package com.example.steppeview.Steps.StepBarItems

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StepTwoContent(onNextStep: () -> Unit, totalSteps: Int) {
    var textValue by remember { mutableStateOf("") }
    val maxWordCount = 500
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Step 2: Enter your text")
        var currentWordCount by remember { mutableStateOf(textValue.split("\\s+".toRegex()).size) }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = textValue,
                    onValueChange = {
                        textValue = it
                        currentWordCount = it.split("\\s+".toRegex()).size
                    },
                    maxLines = Int.MAX_VALUE,
                    textStyle = TextStyle(fontSize = 16.sp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "Mots: $currentWordCount/$maxWordCount",
                color = if (currentWordCount <= maxWordCount) Color.Gray else Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
        Button(
            onClick = {
                // Validate input here if needed
                if (textValue.isNotBlank()) {
                    onNextStep()
                }
            },
            enabled = textValue.isNotBlank(),
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