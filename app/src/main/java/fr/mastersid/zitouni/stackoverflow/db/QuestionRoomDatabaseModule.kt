package fr.mastersid.zitouni.stackoverflow.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn( SingletonComponent :: class )
@Module
object QuestionRoomDatabaseModule {

    @Provides
    fun provideWeatherDao ( questionRoomDatabase : QuestionRoomDatabase ) : QuestionDao {
        return questionRoomDatabase.questionDao()
    }

    @Provides
    @Singleton
    fun provideQuestionRoomDatabase (@ApplicationContext appContext: Context) : QuestionRoomDatabase {
            return Room.databaseBuilder(
                appContext.applicationContext,
                QuestionRoomDatabase::class.java,
                "question_database"
            ).build()
    }
}