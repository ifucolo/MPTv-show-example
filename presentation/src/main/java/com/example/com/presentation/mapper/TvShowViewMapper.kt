package com.example.com.presentation.mapper

import com.example.com.domain.model.TvShow
import com.example.com.presentation.model.TvShowView
import javax.inject.Inject

open class TvShowViewMapper @Inject constructor(): Mapper<TvShowView, TvShow>  {

    override fun mapToView(type: TvShow) = TvShowView(
            id = type.id,
            title = type.title,
            voteAverage = type.voteAverage,
            posterImage = type.posterImage,
            overview = type.overview
    )
}