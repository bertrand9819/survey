

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.steppeview.R
import kotlinx.coroutines.launch
// ... (imports)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionnaireModalBottomSheet(
    totalSteps: Int,
    formSteps: List<@Composable (onNextStep: () -> Unit) -> Unit>
) {
    var currentStep by remember { mutableStateOf(0) }
    var showSuccessMessage by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 100.dp,
        sheetContent = {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                StepBar(currentStep = currentStep, totalSteps = totalSteps)

                formSteps.getOrNull(currentStep)?.invoke {
                    if (currentStep < formSteps.size - 1) {
                        currentStep += 1 // Move to the next step
                    } else {
                        // Last step, show the success message
                        showSuccessMessage = true
                    }
                }
            }
        }
    ) {
        // Content to trigger the BottomSheet
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Start Questionnaire", fontSize = 20.sp)
            Button(
                onClick = {
                    currentStep = 0 // Reset to the first step
                    showSuccessMessage = false // Reset the success message state
                    scope.launch { scaffoldState.bottomSheetState.show() }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Start")
            }
        }
    }

    if (showSuccessMessage) {
        SuccessMessageForm(onDismiss = { showSuccessMessage = false }) // Show the success message if needed
    }
}

// ... (other functions remain the same)


@Composable
fun StepThreeContent(onNextStep: () -> Unit, totalSteps: Int) {
    var selectedEmoji by remember { mutableStateOf("") }
    var showSuccessMessage by remember { mutableStateOf(false) } // Track if success message is shown

    /*StepBar(currentStep = 3, totalSteps = totalSteps)*/
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Step 3: Select an emoji")
        }

        if (!showSuccessMessage) { // Show the EmojiList only if success message is not shown
            EmojiList(onEmojiSelected = { selectedEmoji = it })
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (!showSuccessMessage) { // Show the "Next" button only if success message is not shown
                Button(
                    onClick = {
                        // Validate input here if needed
                        if (selectedEmoji.isNotEmpty()) {
                            onNextStep()
                        }
                        if (selectedEmoji.isNotEmpty() && totalSteps == 3) {
                            showSuccessMessage = true // Show the success message when the button is clicked
                        }
                    },
                    enabled = selectedEmoji.isNotEmpty(),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Next")
                }
            }
        }

        if (showSuccessMessage) {
            SuccessMessageForm(onDismiss = { showSuccessMessage = false }) // Show the success message if needed
        }
    }
}






@Composable
fun StepOneContent(onNextStep: () -> Unit, totalSteps: Int) {
    var selectedOption by remember { mutableStateOf("") }
    var showSuccessMessage by remember { mutableStateOf(false) }
   /* StepBar(currentStep = 1, totalSteps = totalSteps)*/

    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "veuillez choisir ?")
        }

        if (!showSuccessMessage) { // Show the CheckboxList only if success message is not shown
            CheckboxList(onOptionSelected = { selectedOption = it })
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            if (!showSuccessMessage) { // Show the "Next" button only if success message is not shown
                Button(
                    onClick = {
                        // Validate input here if needed
                        if (selectedOption.isNotEmpty()) {
                            onNextStep()
                        }
                        if (selectedOption.isNotEmpty() && totalSteps == 3) {
                            showSuccessMessage = true // Show the success message when the button is clicked
                        }
                    },
                    enabled = selectedOption.isNotEmpty(),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Next")
                }
            }
        }

        if (showSuccessMessage) {
            SuccessMessageForm(onDismiss = { showSuccessMessage = false }) // Show the success message if needed
        }
    }
}

@Composable
fun StepTwoContent(onNextStep: () -> Unit, totalSteps: Int) {
    var textValue by remember { mutableStateOf("") }
    var showSuccessMessage by remember { mutableStateOf(false) }
    val maxWordCount = 500

    /*StepBar(currentStep = 2, totalSteps = totalSteps)*/
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Text(text = "Step 2: Enter your text")

        if (!showSuccessMessage) { // Show the TextField only if success message is not shown
            var currentWordCount by remember { mutableStateOf(textValue.split("\\s+".toRegex()).size) }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    TextField(
                        value = textValue,
                        onValueChange = {
                            textValue = it
                            currentWordCount = it.split("\\s+".toRegex()).size
                        },
                        maxLines = Int.MAX_VALUE,
                        textStyle = TextStyle(fontSize = 16.sp),
                        shape = RoundedCornerShape(8.dp), // Set the shape of the TextField to RoundedCornerShape
                        colors = TextFieldDefaults.colors(disabledContainerColor = Color.Transparent, focusedIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent)
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Mots: $currentWordCount/$maxWordCount",
                    color = if (currentWordCount <= maxWordCount) Color.Gray else Color.Red,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            if (!showSuccessMessage) { // Show the "Next" button only if success message is not shown
                Button(
                    onClick = {
                        // Validate input here if needed
                        if (textValue.isNotBlank()) {
                            onNextStep()
                        }
                        if (textValue.isNotBlank() && totalSteps == 3) {
                            showSuccessMessage = true // Show the success message when the button is clicked
                        }
                    },
                    enabled = textValue.isNotBlank(),
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Next")
                }
            }
        }

        if (showSuccessMessage) {
            SuccessMessageForm(onDismiss = { showSuccessMessage = false }) // Show the success message if needed
        }
    }
}

@Composable
fun StepFourContent(onFinish: () -> Unit, totalSteps: Int) {
    var rating by remember { mutableStateOf(0) }
    var showSuccessMessage by remember { mutableStateOf(false) }
    if (showSuccessMessage) {
        // Show the success message
        SuccessMessageForm(onDismiss = { showSuccessMessage = false })
    } else {
        // Show the rating form
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "Step 4: Rate with stars")
            }
            Spacer(modifier = Modifier.size(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.size(16.dp)) // Add a spacer to adjust the star size
                StarRating(
                    maxRating = 5,
                    rating = rating,
                    onRatingChanged = { newRating ->
                        rating = newRating
                    }
                )
                Spacer(modifier = Modifier.size(16.dp)) // Add a spacer to adjust the star size
            }

            if (!showSuccessMessage) {
                // Show the "Finish" button only if success message is not shown and rating > 0
                Button(
                    onClick = {
                        // Validate input here if needed
                        if (rating > 0) {
                            onFinish()
                            if (totalSteps == 3) {
                                showSuccessMessage = true // Show the success message when the button is clicked
                            }
                        }
                    },
                    enabled = rating > 0, // Enable/disable the button based on rating
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth()
                ) {
                    Text(text = "Finish")
                }
            }
        }
    }
}








@Composable
fun SuccessMessageForm(onDismiss: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Success! Thank you for your rating.")
        }
        Button(
            onClick = {
                onDismiss()
            },
            modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Close")
        }
    }
}

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
            .size(48.dp) // Set the desired star size, e.g., 48.dp
            .clickable(onClick = onClick)
    )
}

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
                            .background(color = if (step <= currentStep) Color.Green else Color.Gray)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))
            }
        }
    }
}

@Composable
fun CheckboxList(onOptionSelected: (String) -> Unit) {
    val options = listOf(
        "Option 1",
        "Option 2",
        "Option 3",
        "Option 4",
        "Option 5"
    )
    options.forEach { option ->
        Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = option, modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.width(12.dp))
            Checkbox(
                checked = option == remember { mutableStateOf("") }.value,
                onCheckedChange = {
                    if (it) onOptionSelected(option)
                },
                modifier = Modifier.weight(1f)
            )

        }
    }
}
@Composable
fun EmojiList(onEmojiSelected: (String) -> Unit) {
    val emojis = listOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground
    )
    val emojiEquivalents = listOf(
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_background,
        R.drawable.ic_launcher_foreground,
        R.drawable.ic_launcher_background
    )
    val emojiLabels = listOf(
        "Smile",
        "Grin",
        "Happy",
        "Kiss",
        "Cool"
    )

    var selectedEmojiIndex by remember { mutableStateOf<Int?>(null) }

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly // Align emojis horizontally
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
                    Image(
                        painter = painterResource(id = if (!isSelected) emojiResId else emojiEquivalents[index]),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize()
                    )
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
@Composable
fun SurveyForms() {
    val totalSteps = 4
    val formSteps: List<@Composable (onNextStep: () -> Unit) -> Unit> = listOf(
        { onNextStep -> StepOneContent(onNextStep, totalSteps) },
        { onNextStep -> StepTwoContent(onNextStep, totalSteps) },
        { onNextStep -> StepThreeContent(onNextStep, totalSteps) },
        { onFinish -> StepFourContent(onFinish, totalSteps) }
    )

    QuestionnaireModalBottomSheet(totalSteps = totalSteps, formSteps = formSteps)

}
@Preview
@Composable
fun PreviewQuestionnaireModalBottomSheet() {

    SurveyForms()

}