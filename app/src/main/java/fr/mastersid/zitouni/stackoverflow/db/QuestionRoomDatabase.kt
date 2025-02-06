package fr.mastersid.zitouni.stackoverflow.db

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.mastersid.zitouni.stackoverflow.data.Question

@Database(
    entities = [Question::class] ,
    version = 1,
    exportSchema = false
)
abstract class QuestionRoomDatabase : RoomDatabase() {
    abstract fun questionDao () : QuestionDao
}