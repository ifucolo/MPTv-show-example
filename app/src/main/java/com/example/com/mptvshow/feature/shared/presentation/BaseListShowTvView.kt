package com.example.com.mptvshow.feature.shared.presentation

import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem

interface BaseListShowTvView {

    fun showResult(result: ArrayList<TvShowItem>)
    fun showError(throwable: Throwable)

}