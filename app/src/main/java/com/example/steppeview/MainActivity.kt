package com.example.steppeview

import SurveyForms
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.example.steppeview.ui.theme.SteppeviewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            SteppeviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SurveyForms()

                }
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true, device = Devices.NEXUS_5, uiMode = UI_MODE_NIGHT_YES, name = "ello")
@Composable
fun PreviewQuestionnaireModalBottomSheet() {
    SurveyForms()
}