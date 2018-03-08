package com.example.com.mptvshow.feature.home.presentation

interface ListTvView {

    fun showResult(result: ArrayList<Any>)
    fun showError(throwable: Throwable)
}