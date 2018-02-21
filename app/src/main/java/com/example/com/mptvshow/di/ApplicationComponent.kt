package com.example.com.mptvshow.di

import com.example.com.mptvshow.MPTvShowApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (NetModule::class), (RepositoryModule::class)])
interface ApplicationComponent {
    fun inject(mPTvShowApplication: MPTvShowApplication) {}
}