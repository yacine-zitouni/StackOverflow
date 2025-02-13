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
import fr.mastersid.zitouni.stackoverflow.viewModel.AnswerListViewModel
import kotlinx.coroutines.launch

@Composable
fun AnswerScreen(modifier: Modifier = Modifier,) {

    Scaffold(
        modifier = modifier,

    ) { innerPadding ->
        AnswerListScreen(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp), // Ajout d'un padding interne

        )


    }
}


