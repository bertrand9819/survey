@file:OptIn(ExperimentalMaterial3Api::class)

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.steppeview.R
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
            modifier = Modifier.padding(top = 16.dp)
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
                        WelcomeForm {
                            showWelcomeForm = false
                            scope.launch { sheetState.expand() }
                        }
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

@Composable
fun QuestionnaireModalBottomSheet(
    totalSteps: Int,
    formSteps: List<@Composable (onNextStep: () -> Unit) -> Unit>
) {

    var currentStep by remember { mutableStateOf(0) }
    var showSuccessMessage by remember { mutableStateOf(false) }
    var showWelcomeForm by remember { mutableStateOf(true) }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 100.dp,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                if (showWelcomeForm) {
                    WelcomeForm {
                        showWelcomeForm = false
                        scope.launch { scaffoldState.bottomSheetState.expand() }
                    }
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
    ) {
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
                    scope.launch{ scaffoldState.bottomSheetState.show() }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Start")
            }
        }
    }
}

@Composable
fun WelcomeForm(onFormCompleted: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.End
    ) {
        Box(
            modifier = Modifier
                .size(48.dp)
                .background(shape = CircleShape, color = Color.Gray.copy(0.3f))
        ) {
            IconButton(
                onClick = {

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
        horizontalAlignment = Alignment.CenterHorizontally
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
                    .height(150.dp)
                    .padding(top = 16.dp, bottom = 8.dp)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "  Souhaitez-vous participer à notre enquête de \n" +
                        "         satisfaction en répondant à quelques \n " +
                        "                                  questions ?",
                fontStyle = FontStyle.Normal
            )
            Spacer(modifier = Modifier.height(30.dp))

        }
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onFormCompleted() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(android.graphics.Color.parseColor("#179138"))),
            elevation = ButtonDefaults.elevatedButtonElevation()

        ) {
            Text(text = "Start Questionnaire")
        }
    }
}
@Composable
fun StepThreeContent(onNextStep: () -> Unit, totalSteps: Int) {
    var selectedEmoji by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Quelle est votre satisfaction  globale \n" +
                        "         à l'égard de nos service ?",
                fontStyle = FontStyle.Normal
            )
            Spacer(modifier = Modifier.width(30.dp))
        }

        EmojiList(onEmojiSelected = { selectedEmoji = it })

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (selectedEmoji.isNotEmpty()) {
                        onNextStep()
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
}

@Composable
fun StepOneContent(onNextStep: () -> Unit, totalSteps: Int) {
    var selectedOption by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "veuillez choisir ?")
        }
        CheckboxList(onOptionSelected = { selectedOption = it })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    if (selectedOption.isNotEmpty()) {
                        onNextStep()
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
}
@Composable
fun StepTwoContent(onNextStep: () -> Unit, totalSteps: Int) {
    var textValue by remember { mutableStateOf("") }
    val maxWordCount = 500
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Step 2: Enter your text")
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
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    )
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
        Button(
            onClick = {
                // Validate input here if needed
                if (textValue.isNotBlank()) {
                    onNextStep()
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
@Composable
fun StepFourContent(onFinish: () -> Unit) {
    var rating by remember { mutableStateOf(0) }
    var showSuccessMessage by remember { mutableStateOf(false) }
    if (!showSuccessMessage) {
        CustomFormContent(
            title = "Step 4: Rate with starssdfksmlflmsd;mlsd;vmsd;msd;md;fm;df",
            buttonText = "Finish",
            onButtonClicked = {
                if (rating > 0) {
                    onFinish()
                    showSuccessMessage = true
                }
            }
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.size(16.dp))
                StarRating(
                    maxRating = 5,
                    rating = rating,
                    onRatingChanged = { newRating ->
                        rating = newRating
                    }
                )
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    } else {
        SuccessMessageForm(onDismiss = { showSuccessMessage = false })
    }
}
@Composable
fun CustomFormContent(
    title: String? = null,
    image: Painter? = null,
    buttonText: String,
    onButtonClicked: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
    ) {
        if (title != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        if (image != null) {
            Image(
                painter = image,
                contentDescription = "Custom Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
        content()
        Button(
            onClick = onButtonClicked,
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = buttonText)
        }
    }
}
@Composable
fun SuccessMessageForm(onDismiss: () -> Unit) {
    CustomFormContent(
        image = painterResource(id = R.drawable.img),
        buttonText = "Close",
        onButtonClicked = onDismiss
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()

                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Congratulations on completing the questionnaire!",
                style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 8.dp)
            )


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
            .size(48.dp)
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

    options.forEachIndexed { index, option ->
        val isChecked = remember { mutableStateOf(false) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = option, modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.width(12.dp))
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .padding(2.dp)
                    .background(if (isChecked.value) Color.Yellow else Color.Transparent)
            ) {
                Checkbox(
                    checked = isChecked.value,
                    onCheckedChange = {
                        isChecked.value = it
                        if (it) onOptionSelected(option)
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }
        }

        if (index < options.size - 0) {
            VerticalDivider()
        }
    }
}

@Composable
fun VerticalDivider() {
    Box(
        modifier = Modifier
            .height(2.dp)
            .fillMaxWidth()
            .background(Color.LightGray.copy(0.6f))
            .padding(horizontal = 88.dp)

    )
}


@Composable
fun slideRatin(onFormCompleted: () -> Unit) {
    var rating by remember { mutableStateOf(0f) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 2.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RatingBar(
            rating = rating,
            onRatingChanged = { newRating ->
                rating = newRating
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 8.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "  Souhaitez-vous participer à notre enquête de \n" +
                        "         satisfaction en répondant à quelques \n " +
                        "                                  questions ?",
                fontStyle = FontStyle.Normal
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = { onFormCompleted() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Start Questionnaire")
        }
    }
}

@Composable
fun RatingBar(
    rating: Float,
    onRatingChanged: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val maxRating = 5

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..maxRating) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = if (i <= rating) Color.Yellow else Color.Gray,
                modifier = Modifier
                    .size(36.dp)
                    .clickable { onRatingChanged(i.toFloat()) }
            )
        }
    }
}

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

@Composable
fun SurveyForms() {
    val totalSteps = 4
    val formSteps: List<@Composable (onNextStep: () -> Unit) -> Unit> = listOf(
        { onNextStep -> StepOneContent(onNextStep, totalSteps) },
        { onNextStep -> StepTwoContent(onNextStep, totalSteps) },
        { onNextStep -> StepThreeContent(onNextStep, totalSteps) },
        { onFinish -> StepFourContent(onFinish) }
    )
    QuestionnaireModalBottomSheet2(totalSteps = totalSteps, formSteps = formSteps)
}

@Composable
fun RadioButtonList(selectedOption: String, onOptionSelected: (String) -> Unit) {
    val options = listOf(
        "Option 1",
        "Option 2",
        "Option 3",
        "Option 4",
        "Option 5"
    )
    options.forEach { option ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = option, modifier = Modifier.weight(2f))
            Spacer(modifier = Modifier.width(12.dp))
            RadioButton(
                selected = (option == selectedOption),
                onClick = {
                    onOptionSelected(option)
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Yellow,
                    unselectedColor = Color.Transparent
                )
            )
        }
    }
}

@Preview
@Composable
fun PreviewQuestionnaireModalBottomSheet() {
    SurveyForms()
}