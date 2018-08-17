package com.example.com.data.store

import com.example.com.data.repository.TvShowCache
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TvShowDaraStoreFactory {

    private val cache = mock<TvShowCache>()
    private val cacheStore = mock<TvShowCacheDataStore>()
    private val remoteStore = mock<TvShowRemoteDataStore>()
    private val factory = TvShowDataStoreFactory(cacheStore, remoteStore)

    @Test
    fun getRemoteStoreRetrieveRemoteSource() {
        assert(factory.getRemoteDataStore() is TvShowRemoteDataStore)
    }

    @Test
    fun getCacheStoreRetrieveCacheSource() {
        assert(factory.getCachedDataStore() is TvShowCacheDataStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenNoCachedData() {
        assert(factory.getDataStore(false , false) is TvShowRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenCachedData() {
        assert(factory.getDataStore(false , false) is TvShowRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnsRemoteSourceWhenCacheExpired() {
        assert(factory.getDataStore(false, false) is TvShowRemoteDataStore)
    }

    @Test
    fun getDataStoreReturnsCacheSourceWhenDataIsCached() {
        stubProjectsCacheAreProjectsCached(true)
        stubProjectsCacheIsProjectsCachedExpired(false)

        assert(factory.getDataStore(true, false) is TvShowCacheDataStore)
    }

    private fun stubProjectsCacheAreProjectsCached(areCached: Boolean) {
        whenever(cache.hasTvShowCached())
                .thenReturn(Single.just(areCached))
    }

    private fun stubProjectsCacheIsProjectsCachedExpired(expired: Boolean) {
        whenever(cache.isTvShowsCacheExpired())
                .thenReturn(Single.just(expired))
    }
}

