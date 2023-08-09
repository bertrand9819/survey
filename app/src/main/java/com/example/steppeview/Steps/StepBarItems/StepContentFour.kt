package com.example.steppeview.Steps.StepBarItems
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.steppeview.Utilis.CustomFormContent
import com.example.steppeview.Utilis.RatingMessage
import com.example.steppeview.Utilis.StarRating

@Composable
fun StepFourContent(onFinish: () -> Unit) {
    var rating by remember { mutableStateOf(0) }
    var showSuccessMessage by remember { mutableStateOf(false) }
    if (!showSuccessMessage) {
        CustomFormContent(
            title = "",
            buttonText = "Finish",
            onButtonClicked = {
                if (rating > 0) {
                    onFinish()
                    showSuccessMessage = true
                }
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {

                    StarRating(
                        maxRating = 5,
                        rating = rating,
                        onRatingChanged = { newRating ->
                            rating = newRating
                        }
                    )

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    RatingMessage(rating = rating)
                }
            }
        }
    } else {
        SuccessMessageForm(onDismiss = { showSuccessMessage = false })
    }
}