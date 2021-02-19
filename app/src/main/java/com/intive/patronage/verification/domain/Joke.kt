package com.intive.patronage.verification.domain

sealed class Joke {
    data class TwoPartJoke(val setup: String, val delivery: String, val category: String) : Joke()
    data class SingleJoke(val joke: String, val category: String) : Joke()

    companion object {
        const val TYPE_SINGLE = "single"
        const val TYPE_TWO_PART = "twopart"
        const val CATEGORY_TO_PROMOTE_AS_GOOD_JOKE = "Programming"
    }
}