package com.example.com.data.mapper

import com.example.com.data.model.TvShowEntity
import com.example.com.domain.model.TvShow
import javax.inject.Inject

open class TvShowMapper @Inject constructor(): EntityMapper<TvShowEntity, TvShow> {

    override fun mapFromEntity(entity: TvShowEntity) = TvShow(
            id = entity.id,
            title = entity.title,
            voteAverage = entity.voteAverage,
            posterImage = entity.posterImage,
            overview = entity.overview
    )

    override fun mapToEntity(domain: TvShow) = TvShowEntity(
            id = domain.id,
            title = domain.title,
            voteAverage = domain.voteAverage,
            posterImage = domain.posterImage,
            overview = domain.overview
    )
}