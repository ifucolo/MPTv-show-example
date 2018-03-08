package com.example.com.mptvshow.feature.detail.domain

import io.reactivex.Single

interface TvShowDetailSource {

    fun fetchSimilarTvShows(id: Int, apiKey: String, page: Int): Single<ArrayList<Any>>

}