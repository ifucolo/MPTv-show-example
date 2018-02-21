package com.example.com.mptvshow.di

import com.example.com.mptvshow.MPTvShowApplication
import com.example.com.mptvshow.feature.list.ui.ListTvShowsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, NetModule::class, RepositoryModule::class))
interface ApplicationComponent {
    fun inject(mPTvShowApplication: MPTvShowApplication) {}
    fun inject(listTvShowsFragment: ListTvShowsFragment) {}
}