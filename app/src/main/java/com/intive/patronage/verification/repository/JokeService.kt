package com.intive.patronage.verification.repository

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface JokeService {

    @GET("joke/any")
    fun queryNewJoke(@Query("type") type: String): Single<JokeJson>

}
