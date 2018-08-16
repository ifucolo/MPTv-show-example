package com.example.com.domain.model

import java.io.Serializable

open class TvShow(
        val id: Int,
        val title: String,
        val voteAverage: Float,
        val posterImage: String,
        val overview: String
)