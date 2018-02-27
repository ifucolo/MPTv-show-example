package com.example.com.mptvshow.feature.shared.infrastructure.model

import com.google.gson.annotations.SerializedName

class TvShowItemPayload {

    @SerializedName("id")
    var id: Int = 0

    @SerializedName("name")
    var title: String = ""

    @SerializedName("vote_average")
    var voteAverage: Float = 0.0f

    @SerializedName("poster_path")
    var posterImage: String = ""

    @SerializedName("overview")
    var overview: String = ""
}