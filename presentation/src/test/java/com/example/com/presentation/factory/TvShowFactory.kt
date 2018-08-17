package com.example.com.presentation.factory

import com.example.com.domain.model.TvShow
import com.example.com.presentation.model.TvShowView

object TvShowFactory {

    fun makeTvShowView() = TvShowView(
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

    fun makeProjectViewList(count: Int): List<TvShowView> {
        val projects = mutableListOf<TvShowView>()
        repeat(count) {
            projects.add(makeTvShowView())
        }
        return projects
    }

    fun makeProjectList(count: Int): List<TvShow> {
        val projects = mutableListOf<TvShow>()
        repeat(count) {
            projects.add(makeTvShow())
        }
        return projects
    }

}