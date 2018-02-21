package com.example.com.mptvshow.di

import com.example.com.mptvshow.feature.list.domain.ListTvShowSource
import com.example.com.mptvshow.feature.list.infrastructure.ListTvShowInfrastructure
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import javax.sql.DataSource


@Module
class RepositoryModule {

    @Provides
    fun providesListTvShowSource(listTvShowInfrastructure: ListTvShowInfrastructure): ListTvShowSource = listTvShowInfrastructure

}