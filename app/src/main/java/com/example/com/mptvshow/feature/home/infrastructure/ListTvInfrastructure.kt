package com.example.com.mptvshow.feature.home.infrastructure

import com.example.com.mptvshow.di.ServerApi
import com.example.com.mptvshow.feature.home.domain.ListTvSource
import com.example.com.mptvshow.feature.shared.infrastructure.mapper.ListTvShowMapper
import io.reactivex.Single
import javax.inject.Inject

class ListTvInfrastructure @Inject constructor(private val serverApi: ServerApi): ListTvSource {

    override fun fetchMostPopularTvShow(apiKey: String, page: Int): Single<ArrayList<Any>> {
        return serverApi.getMostPopular(apiKey, page)
                .map { ListTvShowMapper.map(it) }
    }

}