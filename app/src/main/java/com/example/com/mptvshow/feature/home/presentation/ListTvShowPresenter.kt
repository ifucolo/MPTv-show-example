package com.example.com.mptvshow.feature.home.presentation

import com.example.com.mptvshow.feature.home.domain.ListTvSource
import com.example.com.mptvshow.rx.ReactivePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListTvShowPresenter @Inject constructor(val source: ListTvSource): ReactivePresenter<ListTvView>() {

    fun fetchMostPopularTvShow(apiKey: String, page: Int) {
        disposables += source.fetchMostPopularTvShow(apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )
    }

}