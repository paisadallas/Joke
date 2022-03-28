package com.john.joke.di

import android.content.Context
import androidx.room.Room
import com.john.joke.database.DatabaseRepository
import com.john.joke.database.DatabaseRepositoryImpl
import com.john.joke.database.JokeDao
import com.john.joke.database.JokeDatabase
import com.john.joke.res.JokeAPI
import com.john.joke.res.JokeRepository
import com.john.joke.res.JokeRepositoryImpl
import com.john.joke.viewmodel.JokeViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Implementing modules with Koin
 */

val networkModule = module {

    fun providesLoggingInterceptors(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    fun providesHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor):OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()

    fun providesNetworkServices(okHttpClient: OkHttpClient): JokeAPI =
        Retrofit.Builder()
            .baseUrl(JokeAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(JokeAPI::class.java)

    fun providesJokeRepo(networkServices:JokeAPI):JokeRepository =
        JokeRepositoryImpl(networkServices)

    single { providesLoggingInterceptors() }
    single { providesHttpClient(get()) }
    single { providesNetworkServices(get()) }
    single { providesJokeRepo(get()) }
}

val applicationModule = module {

    fun providesJokeDatabase(context: Context): JokeDatabase =
        Room.databaseBuilder(
            context,
            JokeDatabase::class.java,
            "joke-db"
        ).build()

    fun providesDatabaseDao(jokeDatabase: JokeDatabase): JokeDao =
        jokeDatabase.getJokeDao()

    fun providesDatabaseRepository(databaseDao:JokeDao):DatabaseRepository =
        DatabaseRepositoryImpl(databaseDao)

    single { providesJokeDatabase(androidContext()) }
    single { providesDatabaseDao(get()) }
    single { providesDatabaseRepository(get()) }

}

val viewModelModule = module {
    viewModel {JokeViewModel(get(),get())}
}