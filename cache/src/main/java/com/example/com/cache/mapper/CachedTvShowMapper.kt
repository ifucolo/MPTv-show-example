package com.example.com.cache.mapper

import com.example.com.cache.model.CachedTvShow
import com.example.com.data.model.TvShowEntity
import javax.inject.Inject

class CachedTvShowMapper @Inject constructor(): CacheMapper<CachedTvShow, TvShowEntity> {

    override fun mapFromCached(type: CachedTvShow) = TvShowEntity(
            id = type.id,
            title = type.title,
            voteAverage = type.voteAverage,
            posterImage = type.posterImage,
            overview = type.overview
    )

    override fun mapToCached(type: TvShowEntity) = CachedTvShow(
            id = type.id,
            title = type.title,
            voteAverage = type.voteAverage,
            posterImage = type.posterImage,
            overview = type.overview
    )

}