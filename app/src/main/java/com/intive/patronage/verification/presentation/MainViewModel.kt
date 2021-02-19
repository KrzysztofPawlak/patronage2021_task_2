package com.intive.patronage.verification.presentation

import androidx.lifecycle.MutableLiveData
import com.intive.patronage.verification.db.repository.JokeRepository
import com.intive.patronage.verification.db.toDb
import com.intive.patronage.verification.db.toDomain
import com.intive.patronage.verification.domain.Joke
import com.intive.patronage.verification.repository.Repository
import com.intive.patronage.verification.shared.BaseViewModel
import com.intive.patronage.verification.view.MainActivityError
import com.intive.patronage.verification.view.MainActivityLoading
import com.intive.patronage.verification.view.MainActivityModel
import com.intive.patronage.verification.view.MainActivitySuccess
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val repository: Repository, private val jokeRepository: JokeRepository) : BaseViewModel() {

    val model = MutableLiveData<MainActivityModel>()
    private val jokes = mutableListOf<Joke>()

    init {
        jokeRepository.allJokes
            .toObservable()
            .map { MainActivitySuccess(jokes.apply { addAll(it.toDomain()) }) as MainActivityModel }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { model.value = it }
            .let { addDisposable(it) }
    }

    fun queryNewJoke() {
        val toObservable = repository.queryNewJoke()
            .toObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .publish()
            .autoConnect(2)
        toObservable
            .map { MainActivitySuccess(jokes.apply { add(it) }) as MainActivityModel }
            .startWithItem(MainActivityLoading)
            .onErrorReturnItem(MainActivityError)
            .subscribe { model.value = it }
            .let { addDisposable(it) }
        toObservable
            .onErrorComplete()
            .subscribe { jokeRepository.insert(it.toDb()) }
            .let { addDisposable(it) }
    }

    fun getJokes(): MutableList<Joke> {
        return jokes
    }
}
