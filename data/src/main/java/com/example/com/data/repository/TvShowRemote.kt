package com.example.com.data.repository

import com.example.com.data.model.TvShowEntitty
import com.example.com.domain.model.TvShow
import io.reactivex.Flowable

interface TvShowRemote {

    fun fetchMostPopularTvShows(apiKey: String, page: Int): Flowable<List<TvShowEntitty>>
    fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Flowable<List<TvShowEntitty>>
}