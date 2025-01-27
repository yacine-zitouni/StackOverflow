package fr.mastersid.zitouni.stackoverflow.repository

import android.util.Log
import fr.mastersid.zitouni.stackoverflow.data.Question
import fr.mastersid.zitouni.stackoverflow.webservice.Webservice
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.delay
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val webservice: Webservice
) : Repository {

    override val response: MutableStateFlow<Response> = MutableStateFlow(
        Response.Success(emptyList())
    )

    override suspend fun updateData() {
        val list = webservice.getQuestionList()
        Log.d(" Webservice ", " list : $list ")
        response.emit(Response.Success(list))
    }
}