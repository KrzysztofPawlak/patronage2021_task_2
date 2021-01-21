package com.intive.patronage.verification.presentation

import androidx.lifecycle.MutableLiveData
import com.intive.patronage.verification.domain.Joke
import com.intive.patronage.verification.repository.Repository
import com.intive.patronage.verification.shared.BaseViewModel
import com.intive.patronage.verification.view.MainActivityError
import com.intive.patronage.verification.view.MainActivityLoading
import com.intive.patronage.verification.view.MainActivityModel
import com.intive.patronage.verification.view.MainActivitySuccess
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class MainViewModel(private val repository: Repository) : BaseViewModel() {

    val model = MutableLiveData<MainActivityModel>()
    private val jokes = mutableListOf<Joke>()

    fun queryNewJoke() {
        repository.queryNewJoke()
                .toObservable()
                .map { MainActivitySuccess(jokes.apply { add(it) }) as MainActivityModel }
                .startWithItem(MainActivityLoading)
                .onErrorReturnItem(MainActivityError)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { model.value = it }
                .let { addDisposable(it) }
    }
}
