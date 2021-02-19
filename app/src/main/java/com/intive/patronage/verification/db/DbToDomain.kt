package com.intive.patronage.verification.db

import com.intive.patronage.verification.db.model.JokeEntry
import com.intive.patronage.verification.domain.Joke
import java.lang.UnsupportedOperationException

fun JokeEntry.toDomain(): Joke {
    return when (type) {
        Joke.TYPE_SINGLE -> {
            Joke.SingleJoke(text, category)
        }
        Joke.TYPE_TWO_PART -> {
            Joke.TwoPartJoke(text, text2, category)
        }
        else -> {
            throw UnsupportedOperationException()
        }
    }
}

fun List<JokeEntry>.toDomain(): List<Joke> {
    return map { it.toDomain() }
}