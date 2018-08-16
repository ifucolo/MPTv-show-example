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
class GetSimilarTvShowTest {

    private lateinit var getSimilarTvShows: GetSimilarTvShows
    @Mock lateinit var tvShowsRepository: TvShowRepository
    @Mock lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getSimilarTvShows = GetSimilarTvShows(tvShowsRepository, postExecutionThread)
    }

    @Test
    fun getTvShowsComplete() {
        stubTvShowRepositoryGetSimilarTvShows(Observable.just(TvShowDataFactory.makeTvShowList(10)))

        val testObserver = getSimilarTvShows.buildUseCaseObservable(
                GetSimilarTvShows.Params(TvShowDataFactory.randowInt(), TvShowDataFactory.randowUuid(), TvShowDataFactory.randowInt())
        ).test()
        testObserver.assertComplete()
    }

    @Test
    fun getTvShowsRepository() {
        stubTvShowRepositoryGetSimilarTvShows(Observable.just(TvShowDataFactory.makeTvShowList(10)))

        getSimilarTvShows.buildUseCaseObservable(GetSimilarTvShows.Params(0,"key", 0)).test()
        verify(tvShowsRepository).fetchSimiliarTvShows(0,"key", 0)
    }

    @Test
    fun getTvShowsReturnsData() {
        val tvShows = TvShowDataFactory.makeTvShowList(10)
        stubTvShowRepositoryGetSimilarTvShows(Observable.just(tvShows))

        val testObserver = getSimilarTvShows.buildUseCaseObservable(GetSimilarTvShows.Params(TvShowDataFactory.randowInt(), TvShowDataFactory.randowUuid(), TvShowDataFactory.randowInt())).test()
        testObserver.assertValue(tvShows)

    }



    private fun stubTvShowRepositoryGetSimilarTvShows(observable: Observable<List<TvShow>>) {
        whenever(tvShowsRepository.fetchSimiliarTvShows(any(), any(), any())).thenReturn(observable)
    }
}