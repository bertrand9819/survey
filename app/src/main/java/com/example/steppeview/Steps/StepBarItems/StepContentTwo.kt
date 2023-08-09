package com.example.steppeview.Steps.StepBarItems

import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTwoContent(onNextStep: () -> Unit, totalSteps: Int) {
    var textValue by remember { mutableStateOf("") }
    var currentWordCount by remember { mutableStateOf(0) }
    val maxWordCount = 500

    Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = textValue,
                    onValueChange = {
                        textValue = it
                        currentWordCount =
                            it.split("\\s+".toRegex()).filter { it.isNotBlank() }.size
                    },
                    maxLines = Int.MAX_VALUE,
                    textStyle = TextStyle(fontSize = 16.sp),
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        disabledTextColor = Color.Black,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White
                    ),
                    modifier = Modifier
                        .height(140.dp)
                        .fillMaxWidth()
                        .padding(8.dp)
                        .border(
                            width = 1.dp,
                            color = Color(0xFF626365),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    placeholder = {
                        Text("Eclairer nous un peu sur les raisons de votre note ?")
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, top = 0.dp),
                horizontalArrangement = Arrangement.End,
                // Alignement vertical centré
            ) {
                Text(
                    text = "$currentWordCount/$maxWordCount",
                    color = if (currentWordCount <= maxWordCount) Color.Gray else Color.Red,
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
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
                    colors = ButtonDefaults.buttonColors(
                        containerColor =
                        Color(android.graphics.Color.parseColor("#179138"))
                    ),
                    elevation = ButtonDefaults.elevatedButtonElevation()
                ) {
                    Text(text = "Next")
                }
            }
        }

}