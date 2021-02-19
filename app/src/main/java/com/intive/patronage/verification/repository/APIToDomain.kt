package com.intive.patronage.verification.repository

import com.intive.patronage.verification.domain.Joke

fun JokeJson.toDomain(): Joke {
    return if (type == Joke.TYPE_SINGLE && joke != null) {
        Joke.SingleJoke(joke, category)
    } else if (type == Joke.TYPE_TWO_PART && setup != null && delivery != null) {
        Joke.TwoPartJoke(setup, delivery, category)
    } else {
        throw UnsupportedOperationException()
    }
}
