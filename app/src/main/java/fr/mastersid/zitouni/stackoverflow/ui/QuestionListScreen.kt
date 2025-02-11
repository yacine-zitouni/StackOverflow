package fr.mastersid.zitouni.stackoverflow.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.mastersid.zitouni.stackoverflow.data.Question


@Composable
fun QuestionListScreen(modifier: Modifier, questionList: List<Question>) {


    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(questionList) { question ->
            QuestionRow(question)
        }
    }
}
