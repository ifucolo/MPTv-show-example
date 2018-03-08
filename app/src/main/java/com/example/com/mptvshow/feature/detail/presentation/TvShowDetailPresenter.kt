package com.example.com.mptvshow.feature.detail.presentation

import com.example.com.mptvshow.feature.detail.domain.TvShowDetailSource
import com.example.com.mptvshow.rx.ReactivePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TvShowDetailPresenter @Inject constructor(val source: TvShowDetailSource): ReactivePresenter<TvShowDetailView>() {

    fun fetchSimilarTvShows(tvId: Int, apiKey: String, page: Int) {
        disposables += source.fetchSimilarTvShows(tvId, apiKey, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { view?.showResult(it) },
                        { view?.showError(it) }
                )
    }

}