package com.example.com.mptvshow.feature.list.presentation

import com.example.com.mptvshow.feature.list.domain.entities.TvShowItem

interface ListShowTvView {

    fun showResult(result: ArrayList<TvShowItem>)
    fun showError(throwable: Throwable)

}