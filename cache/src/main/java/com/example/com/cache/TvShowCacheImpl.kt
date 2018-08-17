package com.example.com.cache

import com.example.com.cache.db.TvShowsDatabase
import com.example.com.cache.mapper.CachedTvShowMapper
import com.example.com.cache.model.Config
import com.example.com.data.model.TvShowEntity
import com.example.com.data.repository.TvShowCache
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class TvShowCacheImpl  @Inject constructor(
        private val projectsDatabase: TvShowsDatabase,
        private val mapper: CachedTvShowMapper)
    : TvShowCache {

    override fun clearTvShows(): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().deleteTvShows()
            Completable.complete()
        }
    }

    override fun saveTvShows(projects: List<TvShowEntity>): Completable {
        return Completable.defer {
            projectsDatabase.cachedProjectsDao().insertTvShows(
                    projects.map { mapper.mapToCached(it) })
            Completable.complete()
        }
    }

    override fun fetchMostPopularTvShows(): Flowable<List<TvShowEntity>> {
        return projectsDatabase.cachedProjectsDao().getTvShows()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }

    override fun fetchSimiliarTvShows(): Flowable<List<TvShowEntity>> {
        return projectsDatabase.cachedProjectsDao().getTvShows()
                .map {
                    it.map { mapper.mapFromCached(it) }
                }
    }


    override fun hasTvShowCached(): Single<Boolean> {
        return projectsDatabase.cachedProjectsDao().getTvShows().isEmpty
                .map {
                    !it
                }
    }

    override fun setLastCacheTime(lastCache: Long): Completable {
        return Completable.defer {
            projectsDatabase.configDao().insertConfig(Config(lastCacheTime = lastCache))
            Completable.complete()
        }
    }

    override fun isTvShowsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDatabase.configDao().getConfig()
                .onErrorReturn { Config(lastCacheTime = 0) }
                .map {
                    currentTime - it.lastCacheTime > expirationTime
                }
    }

}