package fr.mastersid.zitouni.stackoverflow.repository

import fr.mastersid.zitouni.stackoverflow.data.Question

sealed interface QuestionResponse {

    object Pending: QuestionResponse

    @JvmInline
    value class Success(val list:  List <Question>): QuestionResponse

    @JvmInline
    value class Error(val message: String) : QuestionResponse


}