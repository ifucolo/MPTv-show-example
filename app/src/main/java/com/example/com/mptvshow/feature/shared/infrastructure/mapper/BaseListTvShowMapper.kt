package com.example.com.mptvshow.feature.shared.infrastructure.mapper

import com.example.com.mptvshow.feature.shared.domain.entities.DetailTvShowItem
import com.example.com.mptvshow.feature.shared.domain.entities.ListTvShowItem
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.shared.infrastructure.model.ListTvShowPayload

object BaseListTvShowMapper {

    fun mapForList(listTvShowPayload: ListTvShowPayload) = ArrayList<Any>().apply {
        listTvShowPayload.results.forEach { itemPayload ->
            add(ListTvShowItem().apply {
                title = itemPayload.title
                voteAverage = itemPayload.voteAverage
                posterImage = itemPayload.posterImage
                id = itemPayload.id
                overview = itemPayload.overview
            })
        }
    }

    fun mapForDetail(listTvShowPayload: ListTvShowPayload) = ArrayList<Any>().apply {
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