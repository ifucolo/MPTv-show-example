package com.example.com.mptvshow.feature.list.infrastructure.model

import com.google.gson.annotations.SerializedName

class TvShowItemPayload {

    @SerializedName("id")
    var id: String = ""

    @SerializedName("name")
    var title: String = ""

    @SerializedName("vote_average")
    var voteAverage: Float = 0.0f

    @SerializedName("poster_path")
    var posterImage: String = ""

    @SerializedName("overview")
    var overview: String = ""
}