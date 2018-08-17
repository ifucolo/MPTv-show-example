package com.example.com.remote.mapper

import com.example.com.data.model.TvShowEntity
import com.example.com.remote.model.TvShowItemPayload
import javax.inject.Inject

class TvShowResponseMapper @Inject constructor(): ModelMapper<TvShowItemPayload, TvShowEntity> {

    override fun mapFromModel(model: TvShowItemPayload) = TvShowEntity(
        id = model.id,
        title = model.title,
        voteAverage = model.voteAverage,
        posterImage = model.posterImage,
        overview = model.overview
    )
}