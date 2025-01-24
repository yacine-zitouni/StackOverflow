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
): Repository {

    override val response: MutableStateFlow<Response> = MutableStateFlow(
        Response.Success(emptyList())
    )

    override suspend fun updateData() {
        webservice.getQuestionList()
            .enqueue(
            object: Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: retrofit2.Response<ResponseBody>
                ) {
                    Log.d(" Webservice ", "OK: ${response.body()?.string()}")
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Log.d(" Webservice ", " Error : ${t.message}")
                }

            }

            )}}