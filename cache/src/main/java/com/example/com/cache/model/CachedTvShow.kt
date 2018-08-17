package com.example.com.cache.model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.example.com.cache.db.TvShowConstants

@Entity(tableName = TvShowConstants.TABLE_NAME)
data class CachedTvShow(
        @PrimaryKey
        @ColumnInfo(name = TvShowConstants.COLUMN_TV_SHOW_ID)
        var id: Int,
        var title: String,
        var voteAverage: Float,
        var posterImage: String,
        var overview: String
)