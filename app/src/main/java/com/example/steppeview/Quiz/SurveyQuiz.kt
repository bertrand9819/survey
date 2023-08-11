package com.example.steppeview.Quiz

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.steppeview.Steps.StepBar
import com.example.steppeview.Steps.StepBarItems.SuccessMessageForm
import com.example.steppeview.Steps.StepBarItems.WelcomeForm
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionnaireModalBottomSheet2(
    totalSteps: Int,
    formSteps: List<@Composable (onNextStep: () -> Unit) -> Unit>
) {
    var currentStep by remember { mutableStateOf(0) }
    var showSuccessMessage by remember { mutableStateOf(false) }
    var showWelcomeForm by remember { mutableStateOf(true) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var temporaryStep by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Start Questionnaire", fontSize = 20.sp)
        Button(
            onClick = {
                showWelcomeForm = true
                showSuccessMessage = false
                scope.launch {
                    sheetState.show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor =
            Color(android.graphics.Color.parseColor("#179138"))
            ),
            elevation = ButtonDefaults.elevatedButtonElevation()
        ) {
            Text(text = "Start")
        }
    }
    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = {
                scope.launch {
                    sheetState.hide()
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (showWelcomeForm) {

                            WelcomeForm(
                                onCloseClicked = {
                                    scope.launch {
                                        sheetState.hide()
                                    }
                                },
                                onFormCompleted = {
                                    showWelcomeForm = false
                                    scope.launch { sheetState.expand() }
                                },
                            )

                    } else if (!showSuccessMessage) {
                        Row(  modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 1.dp),
                            horizontalArrangement = Arrangement.End) {
                            Box(
                                modifier = Modifier
                                    .size(45.dp)
                                    .background(shape = CircleShape, color = Color.Gray.copy(0.2f))
                            ) {
                                IconButton(
                                    onClick = {
                                        scope.launch { sheetState.hide() }
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
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = when (currentStep) {
                                    0 -> "Lorem Ipsum has been the industry's " +
                                            "standard dummy text ever since the 1500s ?"
                                    1 -> "Lorem Ipsum has been the industry's standard dummy text " +
                                            "ever since the 1500s ?"
                                    2 -> "Lorem Ipsum has been the industry's " +
                                            "standard dummy text ever since the 1500s"
                                    3 -> "Lorem Ipsum has been the industry's " +
                                            "standard dummy text?"
                                    else -> ""
                                },
                                textAlign = TextAlign.Center,
                                letterSpacing = TextUnit(0.0F, TextUnitType.Sp),
                                lineHeight = 23.sp,
                                color = Color(0xFF1A1A1A),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif
                            )
                        }
                        Spacer(modifier = Modifier.height(20.dp))

                        // Display the StepBar
                        StepBar(currentStep = currentStep, totalSteps = totalSteps)

                        // Display the form content for the current step
                        formSteps.getOrNull(currentStep)?.invoke {
                            if (currentStep < formSteps.size - 1) {
                                currentStep += 1
                            } else {
                                showSuccessMessage = true
                            }
                        }
                    } else {
                        SuccessMessageForm(onDismiss = {
                            showSuccessMessage = false
                            scope.launch { sheetState.hide() }
                        })
                    }
                }
            }
        )
    }
}
