package com.example.com.mptvshow.di

import com.example.com.mptvshow.feature.shared.domain.BaseListTvShowSource
import com.example.com.mptvshow.feature.shared.infrastructure.BaseListTvShowInfrastructure
import dagger.Module
import dagger.Provides


@Module
class RepositoryModule {

    @Provides
    fun providesListTvShowSource(baseListTvShowInfrastructure: BaseListTvShowInfrastructure): BaseListTvShowSource = baseListTvShowInfrastructure

}