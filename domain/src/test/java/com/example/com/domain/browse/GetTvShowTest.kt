package com.example.com.domain.browse

import com.example.com.domain.executor.PostExecutionThread
import com.example.com.domain.model.TvShow
import com.example.com.domain.repository.TvShowRepository
import com.example.com.domain.test.TvShowDataFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetTvShowTest {

    private lateinit var getTvShows: GetTvShows
    @Mock lateinit var tvShowsRepository: TvShowRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getTvShows = GetTvShows(tvShowsRepository, postExecutionThread)
    }

    @Test
    fun getTvShowsComplete() {
        stubTvShowRepositoryGetTvShows(Observable.just(TvShowDataFactory.makeTvShowList(10)))

        val testObserver = getTvShows.buildUseCaseObservable(GetTvShows.Params(TvShowDataFactory.randowUuid(), TvShowDataFactory.randowInt())).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTvShowsRepository() {
        stubTvShowRepositoryGetTvShows(Observable.just(TvShowDataFactory.makeTvShowList(10)))

        getTvShows.buildUseCaseObservable(GetTvShows.Params("key", 0)).test()
        verify(tvShowsRepository).fetchMostPopularTvShows("key", 0)
    }

    @Test
    fun getTvShowsReturnsData() {
        val tvShows = TvShowDataFactory.makeTvShowList(10)
        stubTvShowRepositoryGetTvShows(Observable.just(tvShows))

        val testObserver = getTvShows.buildUseCaseObservable(GetTvShows.Params(TvShowDataFactory.randowUuid(), TvShowDataFactory.randowInt())).test()
        testObserver.assertValue(tvShows)

    }



    private fun stubTvShowRepositoryGetTvShows(observable: Observable<List<TvShow>>) {
        whenever(tvShowsRepository.fetchMostPopularTvShows(any(), any())).thenReturn(observable)
    }
}