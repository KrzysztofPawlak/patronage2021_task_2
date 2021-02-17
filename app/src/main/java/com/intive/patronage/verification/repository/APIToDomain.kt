package com.intive.patronage.verification.repository

import com.intive.patronage.verification.domain.Joke

fun JokeJson.toDomain(): Joke {
    return if (joke != null) {
        Joke.SingleJoke(joke)
    } else if (setup != null && delivery != null) {
        Joke.TwoPartJoke(setup, delivery)
    } else {
        throw UnsupportedOperationException()
    }
}
