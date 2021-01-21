package com.intive.patronage.verification.view

import com.intive.patronage.verification.domain.Joke

sealed class MainActivityModel

object MainActivityLoading : MainActivityModel()

object MainActivityError : MainActivityModel()

data class MainActivitySuccess(val items: List<Joke>) : MainActivityModel()
