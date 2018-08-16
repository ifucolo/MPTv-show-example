package com.example.com.domain.test

import com.example.com.domain.model.TvShow

object TvShowDataFactory {

    fun randowFloat() = Math.random().toFloat()
    fun randowInt() = Math.random().toInt()
    fun randowUuid() = java.util.UUID.randomUUID().toString()

    fun makeTvShow() = TvShow(
            id = randowInt(),
            title = randowUuid(),
            voteAverage = randowFloat(),
            posterImage = randowUuid(),
            overview = randowUuid()
    )

    fun makeTvShowList(count: Int) = ArrayList<TvShow>().apply { repeat(count) { add(makeTvShow()) } }
}