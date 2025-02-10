package fr.mastersid.zitouni.stackoverflow.usecase

import fr.mastersid.zitouni.stackoverflow.repository.Repository
import javax.inject.Inject

class UpdateDataUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(){
        repository.updateData()
    }
}