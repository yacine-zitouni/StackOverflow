package fr.mastersid.zitouni.stackoverflow.repository

import kotlinx.coroutines.flow.Flow

interface Repository {

    val response: Flow <Response>
    suspend fun updateData()
}