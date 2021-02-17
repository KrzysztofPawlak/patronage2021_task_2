package com.intive.patronage.verification.repository

import com.intive.patronage.verification.domain.Joke
import io.reactivex.rxjava3.core.Single

class NetworkRepository(private val service: JokeService) {

    fun queryNewJoke(): Single<Joke> {
        return service.queryNewJoke()
            .map { it.toDomain() }
    }
}
