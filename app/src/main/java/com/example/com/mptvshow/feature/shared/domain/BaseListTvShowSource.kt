package com.example.com.mptvshow.feature.shared.domain

import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import io.reactivex.Single

interface BaseListTvShowSource {

    fun fetchMostPopularTvShow(apiKey: String, page: Int): Single<ArrayList<TvShowItem>>
    fun fetchSimilarTvShows(id: String, apiKey: String, page: Int): Single<ArrayList<TvShowItem>>

}