package com.example.steppeview.Utilis

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CheckboxList(onOptionSelected: (String) -> Unit) {
    val options = listOf(
        "Lorem Ipsum has been the industry",
        "Lorem Ipsum has been",
        "Lorem Ipsum has been the ",
        "Lorem Ipsum has been the indus",
    )

    options.forEachIndexed { index, option ->
        val isChecked = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = option, modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .padding(2.dp)

            ) {
                Checkbox(

                    checked = isChecked.value,

                    onCheckedChange = {
                        isChecked.value = it
                        if (it) onOptionSelected(option)
                    },

                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }
        }

        if (index < options.size - 1) {
            VerticalDivider()
        }
    }
}
@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(Color.LightGray.copy(0.6f))
            .padding(horizontal = 88.dp)

    )
}