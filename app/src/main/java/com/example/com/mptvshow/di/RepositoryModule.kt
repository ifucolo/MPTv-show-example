package com.example.com.mptvshow.di

import com.example.com.mptvshow.feature.detail.domain.TvShowDetailSource
import com.example.com.mptvshow.feature.detail.infrastructure.TvShowDetailInfrastructure
import com.example.com.mptvshow.feature.home.domain.ListTvSource
import com.example.com.mptvshow.feature.home.infrastructure.ListTvInfrastructure
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    fun providesListTvShowSource(listTvInfrastructure: ListTvInfrastructure): ListTvSource = listTvInfrastructure

    @Provides
    fun providesTvShowDetailSource(tvShowDetailInfrastructure: TvShowDetailInfrastructure): TvShowDetailSource = tvShowDetailInfrastructure

}