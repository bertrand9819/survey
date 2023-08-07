package com.example.steppeview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EmojiList(onEmojiSelected: (String) -> Unit) {
    val emojis = listOf(
        R.drawable.bad_g,
        R.drawable.bad2_gris,
        R.drawable.pas_bien_gris,
        R.drawable.bien_gris,
        R.drawable.love_g
    )
    val emojiEquivalents = listOf(
        R.drawable.bad_r,
        R.drawable.bad2_orange,
        R.drawable.pasbien_jaune,
        R.drawable.bien_jaune,
        R.drawable.love_green
    )
    val emojiLabels = listOf(
        "Très Mauvaise",
        "Mauvaise",
        "Moyenne",
        " Bonne",
        "Très Bonne"
    )
    var selectedEmojiIndex by remember { mutableStateOf<Int?>(null) }
    Spacer(modifier = Modifier.height(21.dp))

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        emojis.forEachIndexed { index, emojiResId ->
            val isSelected = index == selectedEmojiIndex
            Column(
                modifier = Modifier.clickable {
                    onEmojiSelected(emojiLabels[index])
                    selectedEmojiIndex = if (isSelected) null else index
                }
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .border(
                            1.dp,
                            color = if (isSelected) Color.Green else Color.Transparent,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp) // Reduce the size to add padding
                            .background(
                                color = if (isSelected) Color.Green else Color.Transparent,
                                shape = CircleShape
                            )
                            .padding(2.dp), // Add padding here
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = if (!isSelected) emojiResId else emojiEquivalents[index]),
                            contentDescription = null,
                            contentScale = ContentScale.Fit,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }

                if (isSelected) {
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = emojiLabels[index],
                        fontSize = 14.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}