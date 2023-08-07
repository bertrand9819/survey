package com.example.steppeview.Utilis

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StarRating(
    maxRating: Int = 5,
    rating: Int,
    onRatingChanged: (Int) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxRating) {
            val isFilled = i <= rating
            Star(isFilled = isFilled) {
                onRatingChanged(i)
            }
        }
    }
}

@Composable
fun Star(isFilled: Boolean, onClick: () -> Unit) {
    val iconColor = if (isFilled) Color.Yellow else Color.Gray
    Icon(
        imageVector = Icons.Default.Star,
        contentDescription = null,
        tint = iconColor,
        modifier = Modifier
            .size(48.dp)
            .clickable(onClick = onClick)
    )
}

@Composable
fun RatingMessage(rating: Int) {
    val message = when (rating) {
        1 -> "Très mauvais"
        2 -> "Mauvais"
        3 -> "Moyen"
        4 -> "Bien"
        5 -> "Très bien"
        else -> ""
    }

    Text(text = message, fontSize = 16.sp, fontWeight = FontWeight.Bold)
}