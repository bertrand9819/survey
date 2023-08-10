
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.steppeview.Quiz.QuestionnaireModalBottomSheet2
import com.example.steppeview.Steps.StepBarItems.StepFourContent
import com.example.steppeview.Steps.StepBarItems.StepOneContent
import com.example.steppeview.Steps.StepBarItems.StepThreeContent
import com.example.steppeview.Steps.StepBarItems.StepTwoContent
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
@Preview
@Composable
fun PreviewQuestionnaireModalBottomSheet() {
    SurveyForms()
}