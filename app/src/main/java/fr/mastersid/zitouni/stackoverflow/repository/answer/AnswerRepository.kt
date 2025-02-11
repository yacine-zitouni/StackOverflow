package fr.mastersid.zitouni.stackoverflow.repository

import kotlinx.coroutines.flow.Flow

interface AnswerRepository {
    val answerResponse: Flow <AnswerResponse>
    suspend fun updateData()
}