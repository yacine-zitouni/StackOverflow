package fr.mastersid.zitouni.stackoverflow.data

import androidx.room.Entity

data class Answer(val id: Int, val score: Int, val authorName: String, val body: String, val questionId: Int)
