package fr.mastersid.zitouni.stackoverflow.repository

import fr.mastersid.zitouni.stackoverflow.data.Question

sealed interface Response {

    object Pending: Response

    @JvmInline
    value class Success(val list:  List <Question>): Response

    @JvmInline
    value class Error(val message: String) : Response


}