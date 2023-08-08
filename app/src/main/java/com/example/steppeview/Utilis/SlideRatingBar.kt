package com.example.steppeview.Utilis

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*@Composable
fun slideRatin(onFormCompleted: () -> Unit) {
    var rating by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RatingBar(
            rating = rating,
            onRatingChanged = { newRating ->
                rating = newRating
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "  Souhaitez-vous participer à notre enquête de \n" +
                        "         satisfaction en répondant à quelques \n " +
                        "                                  questions ?",
                fontStyle = FontStyle.Normal
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { onFormCompleted() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Questionnaire")
        }
    }
}

@Composable
fun RatingBar(
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val maxRating = 5

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (i <= rating) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onRatingChanged(i.toFloat()) }
            )
        }
    }
}*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun slideRating(){
    var sliderPosition by remember { mutableIntStateOf(0) }
    val interactionSource = remember { MutableInteractionSource() }
    Column {
        Slider(
            modifier = Modifier
                .semantics { contentDescription = "Localized Description" }
                ,
            value = sliderPosition.toFloat(),
            onValueChange = { sliderPosition = it.toInt() },

            valueRange =  0f..5f,
            steps = 5,
            interactionSource = interactionSource,
            onValueChangeFinished = {

            },
            thumb = {
                val shape = RoundedCornerShape(4.dp)
                Box(
                    modifier = Modifier
                        .size(34.dp, 50.dp)
                        .indication(
                            interactionSource = interactionSource,
                            indication = rememberRipple(
                                bounded = false,
                                radius = 20.dp
                            )
                        )
                        .hoverable(interactionSource = interactionSource)
                        .background( Color(android.graphics.Color.parseColor("#179138")), shape),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = sliderPosition.toString(),
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 20.sp
                        )// Changer la couleur du texte ici
                    )
                }
            },
        )
    }
}
