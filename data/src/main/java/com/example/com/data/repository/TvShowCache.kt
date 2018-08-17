package com.example.com.data.repository

import com.example.com.data.model.TvShowEntitty
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface TvShowCache {

    fun clearTvShows(): Completable
    fun saveTvShows(tvShows: List<TvShowEntitty>): Completable
    fun fetchMostPopularTvShows(): Flowable<List<TvShowEntitty>>
    fun fetchSimiliarTvShows(): Flowable<List<TvShowEntitty>>

    fun hasTvShowCached(): Single<Boolean>
    fun setLastCacheTiem(lastCache: Long): Completable
    fun isTvShowsCacheExpired(): Single<Boolean>

}