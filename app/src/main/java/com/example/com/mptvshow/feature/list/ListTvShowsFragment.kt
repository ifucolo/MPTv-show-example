package com.example.com.mptvshow.feature.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.com.mptvshow.R
import com.example.com.mptvshow.feature.shared.fragment.BaseListFragment


class ListTvShowsFragment: BaseListFragment() {

    companion object {

        fun newInstance(): ListTvShowsFragment {
            return ListTvShowsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_tv_show_detail, container, false)

        initView(view)

        return  view
    }

    override fun loadData(page: Int) {
        presenter.fetchMostPopularTvShow(getString(R.string.api_token), page)
    }
}