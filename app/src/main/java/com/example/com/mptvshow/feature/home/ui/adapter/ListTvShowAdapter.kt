package com.example.com.mptvshow.feature.home.ui.adapter

import android.widget.ImageView
import com.example.com.mptvshow.feature.home.ui.adapter.delegate.TvShowDelegate
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.shared.ui.LoaderDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ListTvShowAdapter constructor(list: List<Any>, listTvShowListenner: ListTvShowListenner) : ListDelegationAdapter<List<Any>>() {


    interface ListTvShowListenner {
        fun onCLickTvShow(tvShowItem: TvShowItem, imgCover: ImageView)
    }

    init {
        delegatesManager
                .addDelegate(LoaderDelegate())
                .addDelegate(TvShowDelegate(listTvShowListenner))

        setItems(list)
    }

}