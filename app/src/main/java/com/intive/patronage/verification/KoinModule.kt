package com.intive.patronage.verification

import com.intive.patronage.verification.presentation.MainViewModel
import com.intive.patronage.verification.repository.JokeService
import com.intive.patronage.verification.repository.NetworkRepository
import com.intive.patronage.verification.repository.Repository
import com.intive.patronage.verification.repository.RepositoryImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://v2.jokeapi.dev/"

val mainModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    single<Repository> { RepositoryImpl(get()) }
    single { NetworkRepository(get()) }
    single { createRetrofit() }
    single { createJokeService(get()) }
}

private fun createRetrofit(): Retrofit {
    val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
    val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
}

private fun createJokeService(retrofit: Retrofit): JokeService {
    return retrofit.create(JokeService::class.java)
}
