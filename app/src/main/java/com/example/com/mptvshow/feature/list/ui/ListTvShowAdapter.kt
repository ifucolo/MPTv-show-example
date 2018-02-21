package com.example.com.mptvshow.feature.list.ui

import android.widget.ImageView
import com.example.com.mptvshow.feature.list.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.list.ui.delegate.LoaderDelegate
import com.example.com.mptvshow.feature.list.ui.delegate.TvShowDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class ListTvShowAdapter constructor(list: List<Any>, listGenericListener: ListGenericListener) : ListDelegationAdapter<List<Any>>() {

    interface ListGenericListener {
        fun onCLickTvShow(tvShowItem: TvShowItem, imgCover: ImageView)
    }

    init {
        delegatesManager
                .addDelegate(LoaderDelegate())
                .addDelegate(TvShowDelegate(listGenericListener))

        setItems(list)
    }
}