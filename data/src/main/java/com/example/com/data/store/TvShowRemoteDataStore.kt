package com.example.com.data.store

import com.example.com.data.model.TvShowEntity
import com.example.com.data.repository.TvShowDataStore
import com.example.com.data.repository.TvShowRemote
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

open class TvShowRemoteDataStore @Inject constructor(private val tvShowRemote: TvShowRemote): TvShowDataStore {

    override fun fetchMostPopularTvShows(apiKey: String, page: Int): Flowable<List<TvShowEntity>> {
        return tvShowRemote.fetchMostPopularTvShows(apiKey, page)
    }

    override fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Flowable<List<TvShowEntity>> {
        return tvShowRemote.fetchSimiliarTvShows(id, apiKey, page)
    }

    override fun saveMostPopularTvShows(tvShows: List<TvShowEntity>): Completable {
        throw UnsupportedOperationException("Saving tv shows isn't supported here...")
    }

    override fun clearTvShows(): Completable {
        throw UnsupportedOperationException("Clearing tvShows isn't supported here...")
    }


}