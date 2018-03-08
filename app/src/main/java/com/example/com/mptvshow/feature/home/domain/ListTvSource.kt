package com.example.com.mptvshow.feature.home.domain

import io.reactivex.Single

interface ListTvSource {

    fun fetchMostPopularTvShow(apiKey: String, page: Int): Single<ArrayList<Any>>

}