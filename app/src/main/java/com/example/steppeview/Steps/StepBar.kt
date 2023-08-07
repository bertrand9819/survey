package com.example.steppeview.Steps

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun StepBar(currentStep: Int, totalSteps: Int) {
    CustomStepBar(currentStep = currentStep, totalSteps = totalSteps)
}

@Composable
fun CustomStepBar(currentStep: Int, totalSteps: Int) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (step in 1..totalSteps) {
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .weight(4f),
                    contentAlignment = Alignment.Center
                ) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(color = if (step <= currentStep) Color(android.graphics.Color.parseColor("#179138")) else androidx.compose.ui.graphics.Color.Gray)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}


