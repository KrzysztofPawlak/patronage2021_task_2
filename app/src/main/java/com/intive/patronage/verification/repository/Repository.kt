package com.intive.patronage.verification.repository

import com.intive.patronage.verification.domain.Joke
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun queryNewJoke(): Single<Joke>

}
