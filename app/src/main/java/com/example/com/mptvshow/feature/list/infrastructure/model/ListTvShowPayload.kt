package com.example.com.mptvshow.feature.list.infrastructure.model

import com.google.gson.annotations.SerializedName

class ListTvShowPayload {

    @SerializedName("results")
    var results: ArrayList<TvShowItemPayload> = ArrayList()
}