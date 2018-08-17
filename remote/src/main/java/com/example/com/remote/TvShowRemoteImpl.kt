package com.example.com.remote

import com.example.com.data.model.TvShowEntity
import com.example.com.data.repository.TvShowRemote
import com.example.com.remote.mapper.TvShowResponseMapper
import com.example.com.remote.service.ServerApi
import io.reactivex.Flowable
import javax.inject.Inject

class TvShowRemoteImpl @Inject constructor(private val service: ServerApi, private val mapper: TvShowResponseMapper): TvShowRemote {

    override fun fetchMostPopularTvShows(apiKey: String, page: Int): Flowable<List<TvShowEntity>> {
        return service.getMostPopular(apiKey, page)
                .map {
                    it.results.map { mapper.mapFromModel(it) }
                }
    }

    override fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Flowable<List<TvShowEntity>> {
        return service.getSimilarTvShows(id, apiKey, page)
                .map {
                    it.results.map { mapper.mapFromModel(it) }
                }
    }
}