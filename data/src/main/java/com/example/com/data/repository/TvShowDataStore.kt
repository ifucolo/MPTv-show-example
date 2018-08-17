package com.example.com.data.repository

import com.example.com.data.model.TvShowEntitty
import io.reactivex.Completable
import io.reactivex.Flowable

interface TvShowDataStore {

    fun fetchMostPopularTvShows(apiKey: String, page: Int): Flowable<List<TvShowEntitty>>
    fun saveMostPopularTvShows(tvShows: List<TvShowEntitty>): Completable
    fun clearTvShows(): Completable
    fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Flowable<List<TvShowEntitty>>

}