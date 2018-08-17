package com.example.com.data.mapper

import com.example.com.data.model.TvShowEntitty
import com.example.com.domain.model.TvShow
import javax.inject.Inject

open class TvShowMapper @Inject constructor(): EntityMapper<TvShowEntitty, TvShow> {

    override fun mapFromEntity(entity: TvShowEntitty) = TvShow(
            id = entity.id,
            title = entity.title,
            voteAverage = entity.voteAverage,
            posterImage = entity.posterImage,
            overview = entity.overview
    )

    override fun mapToEntity(domain: TvShow) = TvShowEntitty(
            id = domain.id,
            title = domain.title,
            voteAverage = domain.voteAverage,
            posterImage = domain.posterImage,
            overview = domain.overview
    )
}