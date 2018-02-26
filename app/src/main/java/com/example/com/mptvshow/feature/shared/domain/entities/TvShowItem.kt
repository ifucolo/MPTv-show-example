package com.example.com.mptvshow.feature.shared.domain.entities

import java.io.Serializable

class TvShowItem: Serializable {

    var id: String = ""
    var title: String = ""
    var voteAverage: Float = 0.0f
    var posterImage: String = ""
    var overview: String = ""
}