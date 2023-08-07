package com.example.steppeview.Steps.StepBarItems

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.steppeview.CustomFormContent
import com.example.steppeview.R

@Composable
fun SuccessMessageForm(onDismiss: () -> Unit) {
    CustomFormContent(
        image = painterResource(id = R.drawable.img),
        buttonText = "Close",
        onButtonClicked = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Congratulations on completing the questionnaire!",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )


        }
    }
}