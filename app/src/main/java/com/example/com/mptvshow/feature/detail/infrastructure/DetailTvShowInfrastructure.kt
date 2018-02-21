package com.example.com.mptvshow.feature.detail.infrastructure

import com.example.com.mptvshow.di.ServerApi
import com.example.com.mptvshow.feature.detail.domain.DetailTvShowSource
import com.example.com.mptvshow.feature.list.domain.ListTvShowSource
import com.example.com.mptvshow.feature.list.infrastructure.mapper.ListTvShowMapper
import com.example.com.mptvshow.feature.list.infrastructure.mapper.model.TvShowItem
import io.reactivex.Single
import javax.inject.Inject

class DetailTvShowInfrastructure @Inject constructor(val serverApi: ServerApi): DetailTvShowSource {

    override fun fetchSimilarsTvShow(tvId: String, apiKey: String, page: Int): Single<ArrayList<TvShowItem>> {
        return serverApi.getSimilarTvShows(tvId, apiKey, page)
                .map { ListTvShowMapper.map(it) }
    }
}