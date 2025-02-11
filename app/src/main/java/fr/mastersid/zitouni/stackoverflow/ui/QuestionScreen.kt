package fr.mastersid.zitouni.stackoverflow.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.mastersid.zitouni.stackoverflow.viewModel.QuestionListViewModel
import kotlinx.coroutines.launch

@Composable
fun QuestionScreen(modifier: Modifier = Modifier, questionListViewModel: QuestionListViewModel = viewModel()) {
    val questionList by questionListViewModel.questionList.observeAsState(emptyList())
    val refreshing by questionListViewModel.isUpdating.observeAsState(false)
    val errorMessage by questionListViewModel.errorMessage.observeAsState(null)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var showOnlyUnanswered by rememberSaveable { mutableStateOf(false) }

    val questionListFiltered = if (showOnlyUnanswered) {
        questionList.filter { question -> question.answerCount == 0 }
    } else {
        questionList
    }

    LaunchedEffect(errorMessage) {
        errorMessage?.let {
            scope.launch {
                snackbarHostState.showSnackbar(message = it,
                    duration = SnackbarDuration.Short,
                    actionLabel = "Exit"
                    )
            }
        }
    }
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
                    onCheckedChange = { checked ->  showOnlyUnanswered = checked }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Show only unanswered questions")
            }
        },
        topBar = {
            if (refreshing){
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
            }
                 }
            ,
        floatingActionButton = {
            FloatingActionButton(
                onClick = questionListViewModel::updateList
            ) {
                Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
            }
        }
        ,snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { innerPadding ->
        QuestionListScreen(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp), // Ajout d'un padding interne
            questionList = questionListFiltered
        )


    }
}


