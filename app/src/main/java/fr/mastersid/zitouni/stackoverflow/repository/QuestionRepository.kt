package fr.mastersid.zitouni.stackoverflow.repository

import kotlinx.coroutines.flow.Flow

interface QuestionRepository {
    val questionResponse: Flow <QuestionResponse>
    suspend fun updateData()
}