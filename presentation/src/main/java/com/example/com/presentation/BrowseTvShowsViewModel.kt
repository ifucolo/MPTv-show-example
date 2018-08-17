package com.example.com.presentation

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.com.domain.browse.GetTvShows
import com.example.com.domain.model.TvShow
import com.example.com.presentation.mapper.TvShowViewMapper
import com.example.com.presentation.model.TvShowView
import com.example.com.presentation.state.Resource
import com.example.com.presentation.state.ResourceState
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseTvShowsViewModel @Inject internal constructor(private val getTvShows: GetTvShows?, private val mapper: TvShowViewMapper): ViewModel() {

    private val liveData: MutableLiveData<Resource<List<TvShowView>>> = MutableLiveData()

    override fun onCleared() {
        getTvShows?.dispose()
        super.onCleared()
    }

    fun getTvShows(): LiveData<Resource<List<TvShowView>>> {
        return liveData
    }

    fun fetchTvShows() {
        liveData.postValue(Resource(ResourceState.LOADING, null, null))
        getTvShows?.execute(TvShowSubscriber())
    }


    inner class TvShowSubscriber: DisposableObserver<List<TvShow>>() {
        override fun onNext(t: List<TvShow>) {
            liveData.postValue(Resource(ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onComplete() { }

        override fun onError(e: Throwable) {
            liveData.postValue(Resource(ResourceState.ERROR, null, e.localizedMessage))
        }

    }

}