package com.intive.patronage.verification.repository

import com.intive.patronage.verification.domain.Joke
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(private val networkRepository: NetworkRepository) : Repository {

    override fun queryNewJoke(): Single<Joke> {
        return networkRepository.queryNewJoke()
    }
}
