package com.example.com.mptvshow.feature.shared.infrastructure

import com.example.com.mptvshow.di.ServerApi
import com.example.com.mptvshow.feature.shared.domain.BaseListTvShowSource
import com.example.com.mptvshow.feature.shared.infrastructure.mapper.BaseListTvShowMapper
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import io.reactivex.Single
import javax.inject.Inject

class BaseListTvShowInfrastructure @Inject constructor(val serverApi: ServerApi): BaseListTvShowSource {


    override fun fetchMostPopularTvShow(apiKey: String, page: Int): Single<ArrayList<TvShowItem>> {
        return serverApi.getMostPopular(apiKey, page)
                .map { BaseListTvShowMapper.map(it) }
    }

    override fun fetchSimilarTvShows(id: String, apiKey: String, page: Int): Single<ArrayList<TvShowItem>> {
        return serverApi.getSimilarTvShows(id, apiKey, page)
                .map { BaseListTvShowMapper.map(it) }
    }
}