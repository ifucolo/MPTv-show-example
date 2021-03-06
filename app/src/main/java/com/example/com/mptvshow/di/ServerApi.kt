package com.example.com.mptvshow.di

import com.example.com.mptvshow.feature.shared.infrastructure.model.ListTvShowPayload
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @GET("tv/popular?language=en-US")
    fun getMostPopular(@Query("api_key") apikey: String, @Query("page") page: Int): Single<ListTvShowPayload>

    @GET("tv/{tv_id}/similar?language=en-US")
    fun getSimilarTvShows(@Path("tv_id") tvId: Int, @Query("api_key") apikey: String, @Query("page") page: Int): Single<ListTvShowPayload>


}