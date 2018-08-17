package com.example.com.data.store

import com.example.com.data.model.TvShowEntitty
import com.example.com.data.repository.TvShowCache
import com.example.com.data.repository.TvShowDataStore
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class TvShowCacheDataStore @Inject constructor(private val tvShowCache: TvShowCache): TvShowDataStore {

    override fun fetchMostPopularTvShows(apiKey: String, page: Int): Flowable<List<TvShowEntitty>> {
        return tvShowCache.fetchMostPopularTvShows()
    }

    override fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Flowable<List<TvShowEntitty>> {
        return tvShowCache.fetchSimiliarTvShows()
    }

    override fun saveMostPopularTvShows(tvShows: List<TvShowEntitty>): Completable {
        return tvShowCache.saveTvShows(tvShows)
    }

    override fun clearTvShows(): Completable {
        return tvShowCache.clearTvShows()
    }


}