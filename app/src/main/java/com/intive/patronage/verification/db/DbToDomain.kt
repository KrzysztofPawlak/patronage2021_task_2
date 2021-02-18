package com.intive.patronage.verification.db

import com.intive.patronage.verification.db.model.JokeEntry
import com.intive.patronage.verification.domain.Joke
import java.lang.UnsupportedOperationException

fun JokeEntry.toDomain(): Joke {
    return when (type) {
        "single" -> {
            Joke.SingleJoke(text)
        }
        "twoPart" -> {
            Joke.TwoPartJoke(text, text2)
        }
        else -> {
            throw UnsupportedOperationException()
        }
    }
}

fun List<JokeEntry>.toDomain(): List<Joke> {
    return map { it.toDomain() }
}