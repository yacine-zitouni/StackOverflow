package fr.mastersid.zitouni.stackoverflow.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.ui.theme.StackOverflowTheme
import fr.mastersid.zitouni.stackoverflow.viewModel.ListViewModel

@Composable
fun QuestionScreen(modifier: Modifier, listViewModel: ListViewModel = viewModel()){


    val questionList = listOf(
        Question(1, "Yacine's first question", 5),
        Question(2, "Yacine's second question", 5),
        Question(3, "Yacine's third question", 5)
    )
    Scaffold( modifier = Modifier) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy( 14.dp )
        ) {
            items(questionList) { question ->
                QuestionRow(question)
            }
        }
    }

}

@Preview( showBackground = true , widthDp = 400, name = "test")
@Composable
fun QuestionScreenPreview() {
    StackOverflowTheme {
        QuestionScreen(modifier = Modifier.safeDrawingPadding())
    }
}