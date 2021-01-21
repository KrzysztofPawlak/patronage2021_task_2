package com.intive.patronage.verification.repository

import com.intive.patronage.verification.domain.Joke

fun JokeJson.toDomain(): Joke {
    return Joke(setup, delivery)
}
