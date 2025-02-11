package fr.mastersid.zitouni.stackoverflow.usecase

import fr.mastersid.zitouni.stackoverflow.repository.QuestionRepository
import fr.mastersid.zitouni.stackoverflow.repository.QuestionResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetQuestionResponseFlowUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
){

    suspend operator fun invoke(): Flow<QuestionResponse> {
        return questionRepository.questionResponse
    }
}