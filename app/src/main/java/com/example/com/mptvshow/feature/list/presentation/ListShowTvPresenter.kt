package com.example.com.mptvshow.feature.list.presentation

import com.example.com.mptvshow.feature.list.domain.ListTvShowSource
import com.example.com.mptvshow.rx.ReactivePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListShowTvPresenter @Inject constructor(private val listTvShowSource: ListTvShowSource) : ReactivePresenter<ListShowTvView>() {

    fun fetchMostPopularTvShow(apiKey: String, page: Int) {
        disposables += listTvShowSource.fetchMostPopularTvShow(apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )
    }
}