package com.example.com.mptvshow.feature.detail.domain

import com.example.com.mptvshow.feature.list.infrastructure.mapper.model.TvShowItem
import io.reactivex.Single

interface DetailTvShowSource {

    fun fetchSimilarsTvShow(tvId: String, apiKey: String, page: Int): Single<ArrayList<TvShowItem>>
}