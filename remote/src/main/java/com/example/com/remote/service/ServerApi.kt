package com.example.com.remote.service

import com.example.com.remote.model.ListTvShowPayload
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerApi {

    @GET("tv/popular?language=en-US")
    fun getMostPopular(@Query("api_key") apikey: String, @Query("page") page: Int): Flowable<ListTvShowPayload>

    @GET("tv/{tv_id}/similar?language=en-US")
    fun getSimilarTvShows(@Path("tv_id") tvId: Int, @Query("api_key") apikey: String, @Query("page") page: Int): Flowable<ListTvShowPayload>

}