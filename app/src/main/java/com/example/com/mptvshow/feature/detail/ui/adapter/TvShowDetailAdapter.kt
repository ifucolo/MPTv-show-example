package com.example.com.mptvshow.feature.detail.ui

import android.widget.ImageView
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.shared.ui.LoaderDelegate
import com.example.com.mptvshow.feature.shared.ui.delegate.TvShowDetailDelegate
import com.hannesdorfmann.adapterdelegates3.ListDelegationAdapter

class TvShowDetailAdapter constructor(list: List<Any>, tvShowDetailAdapterListener: TvShowDetailAdapterListener) : ListDelegationAdapter<List<Any>>() {


    interface TvShowDetailAdapterListener {
        fun onCLickTvShow(tvShowItem: TvShowItem, imgCover: ImageView)
    }

    init {
        delegatesManager
                .addDelegate(LoaderDelegate())
                .addDelegate(TvShowDetailDelegate(tvShowDetailAdapterListener))

        setItems(list)
    }

}