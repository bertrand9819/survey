package com.example.steppeview.Quiz

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
    Column(
        modifier = Modifier
            .fillMaxSize()
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
                        .padding(16.dp)
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
                        StepBar(currentStep = currentStep, totalSteps = totalSteps)
                        formSteps.getOrNull(currentStep)?.invoke {
                            if (currentStep < formSteps.size - 1) {
                                currentStep += 1
                            } else {
                                showSuccessMessage = true
                            }
                        }
                    } else {
                        SuccessMessageForm(onDismiss = { showSuccessMessage = false })
                    }
                }
            }
        )
    }
}
