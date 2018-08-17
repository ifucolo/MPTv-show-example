package com.example.com.remote.model

import com.google.gson.annotations.SerializedName

class ListTvShowPayload {

    @SerializedName("results")
    var results: ArrayList<TvShowItemPayload> = ArrayList()
}