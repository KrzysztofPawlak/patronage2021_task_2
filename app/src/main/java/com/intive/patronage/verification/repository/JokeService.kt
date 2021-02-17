package com.intive.patronage.verification.repository

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface JokeService {

    @GET("joke/any")
    fun queryNewJoke(): Single<JokeJson>
}
