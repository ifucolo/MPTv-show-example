package com.example.com.mptvshow.feature.shared.presentation


interface BaseListShowTvView {

    fun showResult(result: ArrayList<Any>)
    fun showError(throwable: Throwable)

}