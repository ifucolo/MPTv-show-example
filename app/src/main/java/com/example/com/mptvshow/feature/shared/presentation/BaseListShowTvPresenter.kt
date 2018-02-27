package com.example.com.mptvshow.feature.shared.presentation

import com.example.com.mptvshow.feature.shared.domain.BaseListTvShowSource
import com.example.com.mptvshow.rx.ReactivePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BaseListShowTvPresenter @Inject constructor(private val baseListTvShowSource: BaseListTvShowSource) : ReactivePresenter<BaseListShowTvView>() {

    fun fetchMostPopularTvShow(apiKey: String, page: Int) {
        disposables += baseListTvShowSource.fetchMostPopularTvShow(apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )
    }

    fun fetchSimilarTvShows(tvId: Int, apiKey: String, page: Int) {
        disposables += baseListTvShowSource.fetchSimilarTvShows(tvId, apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )
    }
}