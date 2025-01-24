package fr.mastersid.zitouni.stackoverflow.webservice

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

private const val BASE_URL = "https://api.stackexchange.com/2.3/"

@Module
@InstallIn( SingletonComponent :: class )
object WebserviceModule {
    @Provides
    fun provideRetrofit () : Retrofit {
        return Retrofit.Builder ()
            .baseUrl ( BASE_URL )
            .build()
    }
    @Provides
    fun provideWebservice ( retrofit : Retrofit ) : Webservice {
        return retrofit.create(Webservice::class.java )
    }
}