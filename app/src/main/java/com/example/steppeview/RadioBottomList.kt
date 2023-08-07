package com.example.steppeview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RadioButtonList(selectedOption: String, onOptionSelected: (String) -> Unit) {
    val options = listOf(
        "Option 1",
        "Option 2",
        "Option 3",
        "Option 4",
        "Option 5"
    )
    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = option, modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.width(12.dp))
            RadioButton(
                selected = (option == selectedOption),
                onClick = {
                    onOptionSelected(option)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Yellow,
                    unselectedColor = Color.Transparent
                )
            )
        }
    }
}
