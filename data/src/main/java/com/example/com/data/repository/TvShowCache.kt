package com.example.com.data.repository

import com.example.com.data.model.TvShowEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface TvShowCache {

    fun clearTvShows(): Completable
    fun saveTvShows(tvShows: List<TvShowEntity>): Completable
    fun fetchMostPopularTvShows(): Flowable<List<TvShowEntity>>
    fun fetchSimiliarTvShows(): Flowable<List<TvShowEntity>>

    fun hasTvShowCached(): Single<Boolean>
    fun setLastCacheTime(lastCache: Long): Completable
    fun isTvShowsCacheExpired(): Single<Boolean>

}