package com.example.com.data.repository

import com.example.com.data.model.TvShowEntity
import io.reactivex.Flowable

interface TvShowRemote {

    fun fetchMostPopularTvShows(apiKey: String, page: Int): Flowable<List<TvShowEntity>>
    fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Flowable<List<TvShowEntity>>
}