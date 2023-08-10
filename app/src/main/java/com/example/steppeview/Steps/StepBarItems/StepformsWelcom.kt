package com.example.steppeview.Steps.StepBarItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.steppeview.R

@Composable
fun WelcomeForm(onFormCompleted: () -> Unit, onCloseClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 1.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .size(47.dp)
                .background(shape = CircleShape, color = Color(0xFFF2F2F2))
        ) {
            IconButton(
                onClick = {
                    onCloseClicked()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Cancel Button",
                    tint = Color.Black
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().align(CenterHorizontally),
            contentAlignment = Alignment.TopCenter
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imgstart),
                    contentDescription = "Welcome Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .padding(top = 16.dp, bottom = 8.dp)
                )

            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                    text = "Souhaitez-vous participer à notre \n enquête de " +
                            "satisfaction en \n répondant à quelques " +
                            "questions ?",
                    textAlign = TextAlign.Center,
                            letterSpacing = TextUnit(0.0F, TextUnitType.Sp),
                lineHeight = 23.sp,
                color = Color(0xFF1A1A1A),
                    fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                    fontFamily = FontFamily.SansSerif
                )

            }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
                Text(
                    text = "Cela ne vous prendra que quelques minutes",
                    textAlign = TextAlign.Center,
                    letterSpacing = TextUnit(0.0F, TextUnitType.Sp),
                    /*lineHeight = 23.sp,*/
                    color = Color(0xFF112113),
                    fontSize = 14.sp
                )


        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onFormCompleted() },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor =
            Color(android.graphics.Color.parseColor("#179138"))
            ),
            elevation = ButtonDefaults.elevatedButtonElevation()

        ) {

            Text(text = "Commencer",
                fontFamily = FontFamily(Font(R.font.intersemibold)),
                letterSpacing = TextUnit(0.0F, TextUnitType.Sp),
                /*lineHeight = 23.sp,*/
                color = Color(0xFFFFFFFF),
                fontSize = 14.sp)
        }
    }
}
