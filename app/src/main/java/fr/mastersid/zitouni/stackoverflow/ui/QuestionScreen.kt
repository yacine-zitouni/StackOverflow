package fr.mastersid.zitouni.stackoverflow.ui

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import fr.mastersid.zitouni.stackoverflow.data.Question
@Composable
fun QuestionScreen(modifier: Modifier){

    val questionList = listOf(
        Question(1, "Yacine's first question", 5),
        Question(2, "Yacine's second question", 5),
        Question(3, "Yacine's third question", 5)
    )
    Scaffold {  }
}
