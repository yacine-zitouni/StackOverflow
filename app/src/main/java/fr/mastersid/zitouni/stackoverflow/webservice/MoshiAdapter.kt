package fr.mastersid.zitouni.stackoverflow.webservice

import com.squareup.moshi.FromJson
import fr.mastersid.zitouni.stackoverflow.data.ListQuestionJson
import fr.mastersid.zitouni.stackoverflow.data.Question

class MoshiAdapter {
    @FromJson
    fun fromJson ( listQuestionJson : ListQuestionJson) : List <Question> {
        return listQuestionJson.items.map { questionJson ->
            Question( questionJson.question_id, questionJson.title, questionJson.answer_count)
        }
    }
}