package fr.mastersid.zitouni.stackoverflow.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.ui.theme.StackOverflowTheme
import fr.mastersid.zitouni.stackoverflow.viewModel.ListViewModel
import androidx.compose.material3.Checkbox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import fr.mastersid.zitouni.stackoverflow.repository.RepositoryDummyImpl

@Composable
fun QuestionScreen(modifier: Modifier, listViewModel: ListViewModel = viewModel()) {
    val questionList by listViewModel.questionList.observeAsState(emptyList())
    val refreshing: Boolean by listViewModel.isUpdating.observeAsState(false)

    val showOnlyUnanswered by listViewModel.showOnlyUnanswered.observeAsState(false)

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(16.dp)
            ) {
                Checkbox(
                    checked = showOnlyUnanswered,
                    onCheckedChange = { checked -> listViewModel.onShowOnlyUnanswered(checked) }
                )
                Text(
                    text = "Show only unanswered questions",
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick =   listViewModel::updateList
            ) {

            }
        }
    ) { innerPadding ->
        QuestionListScreen(
            modifier = Modifier.padding(innerPadding),
            questionList = questionList
        )

    }
}

@Preview( showBackground = true , widthDp = 400, name = "test")
@Composable
fun QuestionScreenPreview() {
    StackOverflowTheme {
        QuestionScreen(modifier = Modifier.safeDrawingPadding(), listViewModel = ListViewModel(RepositoryDummyImpl()))
    }
}