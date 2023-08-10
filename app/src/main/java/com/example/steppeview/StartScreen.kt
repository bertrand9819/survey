package com.example.steppeview

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MyInterface() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 20.dp)
            .background(color = Color(0xFFFAFAFA))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(324.dp)
                .background(Color(0xFFB9E0BD)) // Utilisation de la couleur donnée
        ){
            Spacer(modifier = Modifier.width(112.dp))
            ImageSection()
        }
        Spacer(modifier = Modifier.height(30.dp)) // Ajout d'un espace
        AppContent()
    }
}

@Composable
fun ImageSection() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 112.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(212.dp)
                .width(304.dp)
                .padding(start = 28.dp, end = 28.dp)
                .background(Color(0xFFB9E0BD)) // Utilisation de la couleur donnée
        ) {
            Image(
                painter = painterResource(id = R.drawable.imagesta),
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun TwoButtonsRow(buttonTexts: List<String>,
                  onClickButton1: () -> Unit,
                  onClickButton2: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onClickButton1,
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFEFEF)),
            modifier = Modifier
                .weight(1f)
                .width(159.dp)
                .height(53.dp)
                .padding(end = 8.dp)
        ) {
            Text(text = buttonTexts[0], color = Color(0xFF112113))
        }
        Spacer(modifier = Modifier.width(7.dp))
        Button(
            onClick = onClickButton2,
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFEFEF)),
            modifier = Modifier
                .weight(1f)
                .width(159.dp)
                .height(53.dp)
                .padding(end = 8.dp)
        ) {
            Text(text = buttonTexts[1], color = Color(0xFF112113))
        }
    }
}

@Composable
fun TwoButtonRows(buttonTexts: List<String>,
                  onClickButton1: () -> Unit,
                  onClickButton2: () -> Unit,
                  onClickButton3: () -> Unit,
                  onClickButton4: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = "In app Survey",
            textAlign = TextAlign.Start,
            letterSpacing = TextUnit(0.0F, TextUnitType.Sp),
            /*lineHeight = 23.sp,*/
            color = Color(0xFF1A1A1A),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TwoButtonsRow(buttonTexts = buttonTexts.subList(0, 2),onClickButton1, onClickButton2)
        Spacer(modifier = Modifier.height(4.dp))
        TwoButtonsRow(buttonTexts = buttonTexts.subList(2, 4),onClickButton3, onClickButton4)
    }
}
@Composable
fun AppContent() {
    val contextForToast = LocalContext.current.applicationContext
    val buttonTexts = listOf("Short survey", "Long Survey", "Star rating", "Slider Rating")
    TwoButtonRows(buttonTexts = buttonTexts,
        onClickButton1 = { Toast.makeText(contextForToast, "Short survey!", Toast.LENGTH_SHORT).show() },
        onClickButton2 = { Toast.makeText(contextForToast, "Long Survey!", Toast.LENGTH_SHORT).show() },
        onClickButton3 = { Toast.makeText(contextForToast, "Star rating!", Toast.LENGTH_SHORT).show() },
        onClickButton4 = { Toast.makeText(contextForToast, "Slider Rating!", Toast.LENGTH_SHORT).show() }
    )
}
