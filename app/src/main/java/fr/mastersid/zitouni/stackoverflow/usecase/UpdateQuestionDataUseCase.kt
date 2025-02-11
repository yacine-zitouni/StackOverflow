package fr.mastersid.zitouni.stackoverflow.usecase

import fr.mastersid.zitouni.stackoverflow.repository.QuestionRepository
import javax.inject.Inject

class UpdateQuestionDataUseCase @Inject constructor(
    private val questionRepository: QuestionRepository
) {

    suspend operator fun invoke(){
        questionRepository.updateData()
    }
}