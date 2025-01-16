package fr.mastersid.zitouni.stackoverflow.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.ui.theme.StackOverflowTheme


@Composable
fun QuestionRow (question: Question){
    Row( horizontalArrangement = Arrangement.spacedBy( 16.dp )
    ){
        Text(
            text = question.title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f)
        )
        Text( text = question.answerCount.toString())
    }
}

@Preview( showBackground = true , widthDp = 400, name = "test")
@Composable
fun QuestionRowPreview() {
    StackOverflowTheme {
        QuestionRow(Question(1,"First Question", 3))
    }
}