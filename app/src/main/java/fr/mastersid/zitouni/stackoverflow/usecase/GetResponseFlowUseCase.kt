package fr.mastersid.zitouni.stackoverflow.usecase

import fr.mastersid.zitouni.stackoverflow.repository.Repository
import fr.mastersid.zitouni.stackoverflow.repository.Response
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetResponseFlowUseCase @Inject constructor(
    private val repository: Repository
){

    suspend operator fun invoke(): Flow<Response> {
        return repository.response
    }
}