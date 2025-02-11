package fr.mastersid.zitouni.stackoverflow.repository

import android.util.Log
import fr.mastersid.zitouni.stackoverflow.CoroutineScopeIO
import fr.mastersid.zitouni.stackoverflow.db.QuestionDao
import fr.mastersid.zitouni.stackoverflow.webservice.QuestionWebservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class QuestionRepositoryImpl @Inject constructor(
    private val questionWebservice: QuestionWebservice,
    private val questionDao: QuestionDao,
    @CoroutineScopeIO private val coroutineScopeIO : CoroutineScope
) : QuestionRepository {

    override val questionResponse: MutableStateFlow<QuestionResponse> = MutableStateFlow(
        QuestionResponse.Success(emptyList())
    )

    init {
        coroutineScopeIO.launch {
            questionDao.getQuestionListFlow().collect {
                list -> questionResponse.emit(QuestionResponse.Success(list))
            }
        }
    }
    override suspend fun updateData() {
        try {
            val list = questionWebservice.getQuestionList()
            Log.d("Webservice", "List: $list")

            questionDao.insertAll(list)
            questionResponse.emit(QuestionResponse.Success(list))
        } catch (e: IOException) {
            questionResponse.emit(QuestionResponse.Error("Network error"))
        } catch (e: HttpException) {
            questionResponse.emit(QuestionResponse.Error("Request error"))
        }
    }
}