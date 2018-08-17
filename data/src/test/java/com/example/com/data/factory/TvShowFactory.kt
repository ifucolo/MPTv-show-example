package com.example.com.data.factory

import com.example.com.data.model.TvShowEntitty
import com.example.com.domain.model.TvShow

object TvShowFactory {

    fun makeTvShowEntity() = TvShowEntitty(
            id = DataFactory.randowInt(),
            title = DataFactory.randowUuid(),
            voteAverage = DataFactory.randowFloat(),
            posterImage = DataFactory.randowUuid(),
            overview = DataFactory.randowUuid()
    )

    fun makeTvShow() = TvShow(
            id = DataFactory.randowInt(),
            title = DataFactory.randowUuid(),
            voteAverage = DataFactory.randowFloat(),
            posterImage = DataFactory.randowUuid(),
            overview = DataFactory.randowUuid()
    )

}