package com.example.com.mptvshow.feature.detail.presentation

interface TvShowDetailView {

    fun showResult(result: ArrayList<Any>)
    fun showError(throwable: Throwable)
}