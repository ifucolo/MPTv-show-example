package com.example.com.mptvshow.feature.shared.domain.entities

import java.io.Serializable

open class TvShowItem: Serializable {

    var id: Int = 0
    var title: String = ""
    var voteAverage: Float = 0.0f
    var posterImage: String = ""
    var overview: String = ""
}