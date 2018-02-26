package com.example.com.mptvshow.feature.shared.infrastructure.mapper

import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.shared.infrastructure.model.ListTvShowPayload

object BaseListTvShowMapper {

    fun map(listTvShowPayload: ListTvShowPayload) = ArrayList<TvShowItem>().apply {
        listTvShowPayload.results.forEach { itemPayload ->
            add(TvShowItem().apply {
                title = itemPayload.title
                voteAverage = itemPayload.voteAverage
                posterImage = itemPayload.posterImage
            })
        }
    }
}