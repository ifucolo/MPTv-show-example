package com.example.com.mptvshow.feature.shared.infrastructure.mapper

import com.example.com.mptvshow.feature.detail.domain.entity.DetailTvShowItem
import com.example.com.mptvshow.feature.shared.infrastructure.model.ListTvShowPayload

object TvShowDetailMapper {


    fun map(listTvShowPayload: ListTvShowPayload) = ArrayList<Any>().apply {
        listTvShowPayload.results.forEach { itemPayload ->
            add(DetailTvShowItem().apply {
                title = itemPayload.title
                voteAverage = itemPayload.voteAverage
                posterImage = itemPayload.posterImage
                id = itemPayload.id
                overview = itemPayload.overview
            })
        }
    }
}