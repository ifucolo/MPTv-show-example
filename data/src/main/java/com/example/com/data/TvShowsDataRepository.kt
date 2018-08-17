package com.example.com.data

import com.example.com.data.mapper.TvShowMapper
import com.example.com.data.repository.TvShowCache
import com.example.com.data.store.TvShowDataStoreFactory
import com.example.com.domain.model.TvShow
import com.example.com.domain.repository.TvShowRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

class TvShowsDataRepository @Inject constructor(private val mapper: TvShowMapper, private val cache: TvShowCache, private val factory: TvShowDataStoreFactory): TvShowRepository {


    override fun fetchMostPopularTvShows(apiKey: String, page: Int): Observable<List<TvShow>> {
        return Observable.zip(cache.hasTvShowCached().toObservable(),
                cache.isTvShowsCacheExpired().toObservable(),
                BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>>  { areCached, isExpired ->
                    Pair(areCached, isExpired)
                })
                .flatMap {
                    factory.getDataStore(it.first, it.second)
                            .fetchMostPopularTvShows(apiKey, page)
                            .toObservable()
                            .distinctUntilChanged()
                }
                .flatMap { tvShows ->
                    factory.getCachedDataStore().saveMostPopularTvShows(tvShows)
                            .andThen(Observable.just(tvShows))
                }
                .map {
                    it.map { mapper.mapFromEntity(it) }
                }

    }

    override fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Observable<List<TvShow>> {
        return factory.getCachedDataStore().fetchSimiliarTvShows(id, apiKey, page)
                .toObservable()
                .map { it.map { mapper.mapFromEntity(it) } }
    }
}