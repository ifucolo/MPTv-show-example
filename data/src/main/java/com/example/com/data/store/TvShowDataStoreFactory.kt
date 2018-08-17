package com.example.com.data.store

import com.example.com.data.repository.TvShowDataStore
import javax.inject.Inject

open class TvShowDataStoreFactory @Inject constructor(
        private val tvShowCacheDataStore: TvShowCacheDataStore,
        private val tvShowRemoteDataStore: TvShowRemoteDataStore){

    open fun getDataStore(tvShowsCached: Boolean, cacheExpired: Boolean): TvShowDataStore {
        return if (tvShowsCached && !cacheExpired)
            tvShowCacheDataStore
        else
            tvShowRemoteDataStore
    }

    open fun getCachedDataStore()= tvShowCacheDataStore


    open fun getRemoteDataStore()= tvShowRemoteDataStore

}