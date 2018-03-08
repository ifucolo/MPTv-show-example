package com.example.com.mptvshow.feature.detail.infrastructure

import com.example.com.mptvshow.di.ServerApi
import com.example.com.mptvshow.feature.detail.domain.TvShowDetailSource
import com.example.com.mptvshow.feature.home.domain.ListTvSource
import com.example.com.mptvshow.feature.shared.infrastructure.mapper.TvShowDetailMapper
import io.reactivex.Single
import javax.inject.Inject

class TvShowDetailInfrastructure @Inject constructor(private val serverApi: ServerApi): TvShowDetailSource {

    override fun fetchSimilarTvShows(id: Int, apiKey: String, page: Int): Single<ArrayList<Any>> {
        return serverApi.getSimilarTvShows(id, apiKey, page)
                .map { TvShowDetailMapper.map(it) }
    }
}