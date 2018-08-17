package com.example.com.data.repository

import com.example.com.data.model.TvShowEntity
import io.reactivex.Completable
import io.reactivex.Flowable

interface TvShowDataStore {

    fun fetchMostPopularTvShows(apiKey: String, page: Int): Flowable<List<TvShowEntity>>
    fun saveMostPopularTvShows(tvShows: List<TvShowEntity>): Completable
    fun clearTvShows(): Completable
    fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Flowable<List<TvShowEntity>>

}