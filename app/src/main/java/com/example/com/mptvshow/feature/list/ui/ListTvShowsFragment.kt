package com.example.com.mptvshow.feature.list.ui

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.example.com.mptvshow.R
import com.example.com.mptvshow.feature.BaseFragment
import com.example.com.mptvshow.feature.list.domain.entities.Loader
import com.example.com.mptvshow.feature.list.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.list.presentation.ListShowTvPresenter
import com.example.com.mptvshow.feature.list.presentation.ListShowTvView
import com.example.com.mptvshow.widget.EndlessRecyclerViewScrollListener
import javax.inject.Inject
import android.support.v7.widget.GridLayoutManager
import com.example.com.mptvshow.MPTvShowApplication


class ListTvShowsFragment: BaseFragment(), ListShowTvView {

    @Inject
    lateinit var presenter: ListShowTvPresenter

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    var items: ArrayList<Any> =  ArrayList()
    lateinit var listTvShowAdapter : ListTvShowAdapter
    private var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener? = null

    companion object {

        fun newInstance(): ListTvShowsFragment {
            return ListTvShowsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        items.clear()
        listTvShowAdapter = ListTvShowAdapter(items, this)

        insertLoader()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_tv_show_detail, container, false)

        MPTvShowApplication().get().getCoreComponent().inject(this)
        presenter.bind(this)

        ButterKnife.bind(this, view)
        setupRecycler()

        return  view
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    protected fun setupRecycler() = with(recyclerView) {
        val oldState = endlessRecyclerViewScrollListener?.state

        val linearLayoutManager = GridLayoutManager(activity, 2)
        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadData(page)
            }
        }
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        endlessRecyclerViewScrollListener?.state = oldState

        layoutManager = linearLayoutManager
        adapter = listTvShowAdapter
        addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    fun loadData(page: Int) {
        presenter.fetchMostPopularTvShow(getString(R.string.api_token), page)
    }

    override fun showResult(result: ArrayList<TvShowItem>) {
        removeLoader()

        if(result.isEmpty())
            return

        val size = items.size

        items.addAll(result)

        listTvShowAdapter.notifyItemRangeChanged(size, items.size)

        insertLoader()
    }

    override fun showError(throwable: Throwable) {

    }

    private fun insertLoader() {
        items.add(Loader())
        listTvShowAdapter.notifyItemInserted(items.size - 1)
    }


    private fun removeLoader() {
        if (items.isEmpty())
            return

        val lastIndex = items.size - 1
        if (items[lastIndex] is Loader) {
            items.removeAt(lastIndex)

            listTvShowAdapter.notifyItemRemoved(lastIndex)
        }
    }


}