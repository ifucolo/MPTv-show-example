package com.example.com.mptvshow.feature.list.infrastructure.mapper

import com.example.com.mptvshow.feature.list.infrastructure.mapper.model.TvShowItem
import com.example.com.mptvshow.feature.list.infrastructure.model.ListTvShowPayload

object ListTvShowMapper {

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