package fr.mastersid.zitouni.stackoverflow.repository

import fr.mastersid.zitouni.stackoverflow.data.Question
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.delay
import javax.inject.Inject

class RepositoryDummyImpl @Inject constructor(): Repository {

    override val response: MutableStateFlow<Response> = MutableStateFlow(
        Response.Success(emptyList())
    )

    override suspend fun updateData() {
        response.emit(Response.Pending)
        delay(1000)
        response.emit(Response.Success(
            listOf(
                Question(1, "How to learn Jetpack Compose?", 5),
                Question(2, "What is the best way to manage state in Compose?", 3),
                Question(3, "How to use Scaffold in Compose?", 2),
                Question(4, "What are Compose modifiers?", 8),
                Question(5, "How to handle navigation in Jetpack Compose?", 6),
                Question(6, "What is a Composable function?", 7),
                Question(7, "How to apply themes in Jetpack Compose?", 1),
                Question(8, "What is the role of ViewModel in Compose?", 4),
                Question(9, "How to optimize performance in Compose apps?", 0),
                Question(10, "What are the benefits of using Compose over XML?", 9),
                Question(11, "How to handle lists in Compose?", 2),
                Question(12, "What is LazyColumn in Compose?", 3),
                Question(13, "How to use Animations in Compose?", 10),
                Question(14, "How to handle user input in Compose?", 6),
                Question(15, "How to debug Compose applications?", 1),
                Question(16, "What are Slots APIs in Compose?", 0),
                Question(17, "How to use Canvas in Jetpack Compose?", 7),
                Question(18, "How to integrate Compose with existing views?", 5),
                Question(19, "What are Compose side effects?", 4),
                Question(20, "How to handle configuration changes in Compose?", 8),

        )
        ))

    }
}