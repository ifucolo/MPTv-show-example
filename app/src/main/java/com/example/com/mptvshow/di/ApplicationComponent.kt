package com.example.com.mptvshow.di

import com.example.com.mptvshow.MPTvShowApplication
import com.example.com.mptvshow.feature.detail.ui.TvShowDetailFragment
import com.example.com.mptvshow.feature.home.ui.ListTvShowsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetModule::class), (RepositoryModule::class)])
interface ApplicationComponent {
    fun inject(mPTvShowApplication: MPTvShowApplication) {}
    fun inject(listTvShowsFragment: ListTvShowsFragment) {}
    fun inject(tvShowDetailFragment: TvShowDetailFragment) {}
}