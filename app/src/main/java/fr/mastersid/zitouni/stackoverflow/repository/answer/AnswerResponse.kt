package fr.mastersid.zitouni.stackoverflow.repository.answer

import fr.mastersid.zitouni.stackoverflow.data.Question

sealed interface AnswerResponse {

    object Pending: AnswerResponse

    @JvmInline
    value class Success(val list:  List <Question>): AnswerResponse

    @JvmInline
    value class Error(val message: String) : AnswerResponse


}