package fr.mastersid.zitouni.stackoverflow.webservice

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Webservice {

    @GET("questions?")
    fun getQuestionList(
        @Query("pagesize")  pagesize:Int = 20,
        @Query("order") order: String = "desc",
        @Query("sort") sort: String = "Activity",
        @Query("site") site : String = "stackoverflow"
    ): Call<ResponseBody>
}