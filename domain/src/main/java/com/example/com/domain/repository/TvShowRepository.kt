package com.example.com.domain.repository

import com.example.com.domain.model.TvShow
import io.reactivex.Observable

interface TvShowRepository {

    fun fetchMostPopularTvShows(apiKey: String, page: Int): Observable<List<TvShow>>
    fun fetchSimiliarTvShows(id: Int, apiKey: String, page: Int): Observable<List<TvShow>>
}