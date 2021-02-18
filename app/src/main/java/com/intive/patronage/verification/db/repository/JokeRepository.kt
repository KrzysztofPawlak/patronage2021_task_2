package com.intive.patronage.verification.db.repository

import android.app.Application
import com.intive.patronage.verification.db.AppDatabase
import com.intive.patronage.verification.db.model.JokeEntry
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class JokeRepository(application: Application) {
    private var database = AppDatabase.getAppDatabase(application)!!

    private var jokeDao = database.jokeDao()

    val allJokes: Single<List<JokeEntry>> = jokeDao.getAll()

    fun insert(jokeEntry: JokeEntry) = GlobalScope.launch(Dispatchers.Main) {
        async(Dispatchers.IO) {
            jokeDao.insert(jokeEntry)
        }
    }
}