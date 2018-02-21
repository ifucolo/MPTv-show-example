package com.example.com.mptvshow.feature.list.infrastructure

import com.example.com.mptvshow.di.ServerApi
import com.example.com.mptvshow.feature.list.domain.ListTvShowSource
import com.example.com.mptvshow.feature.list.infrastructure.mapper.ListTvShowMapper
import com.example.com.mptvshow.feature.list.domain.entities.TvShowItem
import io.reactivex.Single
import javax.inject.Inject

class ListTvShowInfrastructure @Inject constructor(val serverApi: ServerApi): ListTvShowSource {


    override fun fetchMostPopularTvShow(apiKey: String, page: Int): Single<ArrayList<TvShowItem>> {
        return serverApi.getMostPopular(apiKey, page)
                .map { ListTvShowMapper.map(it) }
    }
}