package com.intive.patronage.verification.repository

import com.intive.patronage.verification.domain.Joke
import io.reactivex.rxjava3.core.Single

private const val JOKE_TYPE = "twopart"

class NetworkRepository(private val service: JokeService) {

    fun queryNewJoke(): Single<Joke> {
        return service.queryNewJoke(JOKE_TYPE)
            .map { it.toDomain() }
    }
}
