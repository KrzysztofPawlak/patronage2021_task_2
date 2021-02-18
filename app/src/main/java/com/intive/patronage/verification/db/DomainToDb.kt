package com.intive.patronage.verification.db

import com.intive.patronage.verification.db.model.JokeEntry
import com.intive.patronage.verification.domain.Joke

fun Joke.toDb(): JokeEntry {
    return when (this) {
        is Joke.TwoPartJoke -> {
            JokeEntry(
                0,
                setup,
                delivery,
                category = "test",
                type = "twoPart"
            )
        }
        is Joke.SingleJoke -> {
            JokeEntry(
                0,
                joke,
                text2 = "",
                category = "test",
                type = "single"
            )
        }
    }
}