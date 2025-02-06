package fr.mastersid.zitouni.stackoverflow.repository

import android.util.Log
import fr.mastersid.zitouni.stackoverflow.CoroutineScopeIO
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.db.QuestionDao
import fr.mastersid.zitouni.stackoverflow.webservice.Webservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class RepositoryImpl @Inject constructor(
    private val webservice: Webservice,
    private val questionDao: QuestionDao,
    @CoroutineScopeIO private val coroutineScopeIO : CoroutineScope
) : Repository {

    override val response: MutableStateFlow<Response> = MutableStateFlow(
        Response.Success(emptyList())
    )

    init {
        coroutineScopeIO.launch {
            questionDao.getQuestionListFlow().collect {
                list -> response.emit(Response.Success(list))
            }
        }
    }
    override suspend fun updateData() {
        try {
            val list = webservice.getQuestionList()
            Log.d("Webservice", "List: $list")

            questionDao.insertAll(list)
            response.emit(Response.Success(list))
        } catch (e: IOException) {
            response.emit(Response.Error("Network error"))
        } catch (e: HttpException) {
            response.emit(Response.Error("Request error"))
        }
    }
}