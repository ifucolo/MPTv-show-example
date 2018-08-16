package com.example.com.domain.browse

import com.example.com.domain.ObservableUseCase
import com.example.com.domain.executor.PostExecutionThread
import com.example.com.domain.model.TvShow
import com.example.com.domain.repository.TvShowRepository
import io.reactivex.Observable
import javax.inject.Inject

open class GetSimilarTvShows @Inject constructor(
        private val tvShowRepository: TvShowRepository,
        postExecutionThread: PostExecutionThread)
    : ObservableUseCase<List<TvShow>, GetSimilarTvShows.Params>(postExecutionThread) {

    public override fun buildUseCaseObservable(params: Params?): Observable<List<TvShow>> {
        if (params == null)
            throw IllegalArgumentException("Params cant be null")

        return tvShowRepository.fetchSimiliarTvShows(params.id, params.apiKey, params.page)
    }

    data class Params constructor(val id: Int, val apiKey: String, val page: Int) {
        companion object {
            fun forTvShow(id: Int, apiKey: String, page: Int): Params{
                return Params(id, apiKey, page)
            }
        }
    }
}