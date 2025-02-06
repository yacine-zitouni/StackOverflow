package fr.mastersid.zitouni.stackoverflow.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.repository.RepositoryDummyImpl
import fr.mastersid.zitouni.stackoverflow.ui.theme.StackOverflowTheme
import fr.mastersid.zitouni.stackoverflow.viewModel.ListViewModel

@Composable
fun QuestionScreen(modifier: Modifier = Modifier, listViewModel: ListViewModel = viewModel()) {
    val questionList by listViewModel.questionList.observeAsState(emptyList())
    val refreshing by listViewModel.isUpdating.observeAsState(false)
    val showOnlyUnanswered by listViewModel.showOnlyUnanswered.observeAsState(false)

    Scaffold(
        modifier = modifier,
        bottomBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Checkbox(
                    checked = showOnlyUnanswered,
                    onCheckedChange = { checked -> listViewModel.onShowOnlyUnanswered(checked) }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Show only unanswered questions")
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = listViewModel::updateList
            ) {
                Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
            }
        }
    ) { innerPadding ->
        QuestionListScreen(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp), // Ajout d'un padding interne
            questionList = questionList
        )
    }
}

@Preview(showBackground = true, widthDp = 400, name = "test")
@Composable
fun QuestionScreenPreview() {
    StackOverflowTheme {
        QuestionScreen(
            modifier = Modifier.safeDrawingPadding(),
            listViewModel = ListViewModel(RepositoryDummyImpl())
        )
    }
}
