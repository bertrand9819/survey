package com.example.steppeview

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.steppeview.Quiz.QuestionnaireModalBottomSheet2
import com.example.steppeview.Steps.StepBarItems.StepFourContent
import com.example.steppeview.Steps.StepBarItems.StepOneContent
import com.example.steppeview.Steps.StepBarItems.StepThreeContent
import com.example.steppeview.Steps.StepBarItems.StepTwoContent
import com.example.steppeview.Utilis.slideRating
import kotlinx.coroutines.launch

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
        Spacer(modifier = Modifier.height(30.dp))
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

            color = Color(0xFF1A1A1A),
            fontSize = 38.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = Modifier.height(20.dp))
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
fun SingleButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEFEFEF)),
        modifier = Modifier
            .width(159.dp)
            .height(53.dp)
            .padding(end = 8.dp)
    ) {
        Text(text = text, color = Color(0xFF112113))
    }
}
@Composable
fun TwoButtonsRow(
    buttonTexts: List<String>,
    onClickButton1: () -> Unit,
    onClickButton2: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        SingleButton(buttonTexts[0], onClickButton1)
        Spacer(modifier = Modifier.width(7.dp))
        SingleButton(buttonTexts[1], onClickButton2)
    }
}
@Composable
fun TwoButtonRows(buttonTexts: List<String>, onClickCallbacks: List<() -> Unit>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        TwoButtonsRow(buttonTexts = buttonTexts.subList(0, 2),
            onClickButton1 = onClickCallbacks[0],
            onClickButton2 = onClickCallbacks[1])
        Spacer(modifier = Modifier.height(4.dp))
        TwoButtonsRow(buttonTexts = buttonTexts.subList(2, 4),
            onClickButton1 = onClickCallbacks[2],
            onClickButton2 = onClickCallbacks[3])
    }
}
@Composable
fun ShortSurveyScreen() {

}


@Composable
fun LongSurveyScreen() {

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    val buttonTexts = listOf("Short survey", "Long Survey", "Star rating", "Slider Rating")
    val navController= rememberNavController()
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    NavHost(navController, startDestination = "home") {
        composable("home")
        { TwoButtonRows(buttonTexts = buttonTexts, onClickCallbacks = onClickCallbacks(navController)) }
        composable("shortSurvey") {
            ShortSurveyScreen()
        }
        composable("longSurvey") {

            val totalSteps = 4
            val formSteps: List<@Composable (onNextStep: () -> Unit) -> Unit> = listOf(
                { onNextStep -> StepOneContent(onNextStep, totalSteps) },
                { onNextStep -> StepTwoContent(onNextStep, totalSteps) },
                { onNextStep -> StepThreeContent(onNextStep, totalSteps) },
                { onFinish -> StepFourContent(onFinish) }
            )
            QuestionnaireModalBottomSheet2(totalSteps = totalSteps, formSteps = formSteps)



        }
        composable("Slider Rating") {

            ModalBottomSheet(
                sheetState = sheetState,
                onDismissRequest = {
                    scope.launch {
                        sheetState.hide()
                    }
                },
                content = {
                    slideRating()
                }
            )

        }
    }
}
private fun onClickCallbacks(navController: NavHostController): List<() -> Unit> {
    return listOf(
        { navController.navigate("shortSurvey") },
        { navController.navigate("longSurvey") },
        { navController.navigate("Slider Rating")},
        { navController.navigate("Star rating") }
    )
}













