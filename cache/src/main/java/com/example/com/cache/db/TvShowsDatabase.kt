package com.example.com.cache.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.com.cache.dao.CacheTvShowDao
import com.example.com.cache.dao.ConfigDao
import com.example.com.cache.model.CachedTvShow
import com.example.com.cache.model.Config
import javax.inject.Inject

@Database(entities = [CachedTvShow::class, Config::class], version = 1)
abstract class TvShowsDatabase @Inject constructor(): RoomDatabase() {

    abstract fun cachedProjectsDao(): CacheTvShowDao

    abstract fun configDao(): ConfigDao

    companion object {

        private var INSTANCE: TvShowsDatabase? = null
        private val lock = Any()

        fun getInstance(context: Context): TvShowsDatabase {
            if (INSTANCE == null) {
                synchronized(lock) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                                TvShowsDatabase::class.java, "tv_shows.db")
                                .build()
                    }
                    return INSTANCE as TvShowsDatabase
                }
            }
            return INSTANCE as TvShowsDatabase
        }
    }

}