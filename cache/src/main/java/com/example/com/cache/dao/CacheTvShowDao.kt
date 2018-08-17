package com.example.com.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.com.cache.db.TvShowConstants.DELETE_TV_SHOWS
import com.example.com.cache.db.TvShowConstants.QUERY_TV_SHOW
import com.example.com.cache.model.CachedTvShow
import io.reactivex.Flowable

@Dao
abstract class CacheTvShowDao {

    @Query(QUERY_TV_SHOW)
    @JvmSuppressWildcards
    abstract fun getTvShows(): Flowable<List<CachedTvShow>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    abstract fun insertTvShows(projects: List<CachedTvShow>)

    @Query(DELETE_TV_SHOWS)
    abstract fun deleteTvShows()


}