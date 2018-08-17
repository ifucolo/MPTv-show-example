package com.example.com.presentation.browse

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.com.domain.browse.GetTvShows
import com.example.com.domain.model.TvShow
import com.example.com.presentation.BrowseTvShowsViewModel
import com.example.com.presentation.factory.DataFactory
import com.example.com.presentation.factory.TvShowFactory
import com.example.com.presentation.mapper.TvShowViewMapper
import com.example.com.presentation.model.TvShowView
import com.example.com.presentation.state.ResourceState
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Captor

@RunWith(JUnit4::class)
class BrowseTvShowViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    var getTvShow = mock<GetTvShows>()
    var tvShowtMapper = mock<TvShowViewMapper>()
    var tvShowViewModel = BrowseTvShowsViewModel(getTvShow, tvShowtMapper)

    @Captor
    val captor = argumentCaptor<DisposableObserver<List<TvShow>>>()

    @Test
    fun fetchTvShowExecutesUseCase() {
        tvShowViewModel.fetchTvShows()

        verify(getTvShow, times(1)).execute(any(), eq(null))
    }

    @Test
    fun fetchTvShowsReturnsSuccess() {
        val projects = TvShowFactory.makeProjectList(2)
        val projectViews = TvShowFactory.makeProjectViewList(2)
        stubTvShowMapperMapToView(projectViews[0], projects[0])
        stubTvShowMapperMapToView(projectViews[1], projects[1])

        tvShowViewModel.fetchTvShows()

        verify(getTvShow).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        Assert.assertEquals(ResourceState.SUCCESS,
                tvShowViewModel.getTvShows().value?.status)
    }

    @Test
    fun fetchTvShowsReturnsData() {
        val projects = TvShowFactory.makeProjectList(2)
        val projectViews = TvShowFactory.makeProjectViewList(2)
        stubTvShowMapperMapToView(projectViews[0], projects[0])
        stubTvShowMapperMapToView(projectViews[1], projects[1])

        tvShowViewModel.fetchTvShows()

        verify(getTvShow).execute(captor.capture(), eq(null))
        captor.firstValue.onNext(projects)

        Assert.assertEquals(projectViews,
                tvShowViewModel.getTvShows().value?.data)
    }

    @Test
    fun fetchTvShowsReturnsError() {
        tvShowViewModel.fetchTvShows()

        verify(getTvShow).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException())

        Assert.assertEquals(ResourceState.ERROR,
                tvShowViewModel.getTvShows().value?.status)
    }

    @Test
    fun fetchTvShowsReturnsMessageForError() {
        val errorMessage = DataFactory.randowUuid()
        tvShowViewModel.fetchTvShows()

        verify(getTvShow).execute(captor.capture(), eq(null))
        captor.firstValue.onError(RuntimeException(errorMessage))

        Assert.assertEquals(errorMessage,
                tvShowViewModel.getTvShows().value?.message)
    }

    private fun stubTvShowMapperMapToView(tvShowView: TvShowView, tvShow: TvShow) {
        whenever(tvShowtMapper.mapToView(tvShow))
                .thenReturn(tvShowView)
    }
}