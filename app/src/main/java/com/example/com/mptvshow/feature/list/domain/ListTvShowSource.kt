package com.example.com.mptvshow.feature.list.domain

import com.example.com.mptvshow.feature.list.infrastructure.mapper.model.TvShowItem
import io.reactivex.Single

interface ListTvShowSource {

    fun fetchMostPopularTvShow(apiKey: String, page: Int): Single<ArrayList<TvShowItem>>
}