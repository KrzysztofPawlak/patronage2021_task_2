package com.intive.patronage.verification.domain

sealed class Joke {
    data class TwoPartJoke(val setup: String, val delivery: String) : Joke()
    data class SingleJoke(val joke: String) : Joke()
}