package com.example.com.mptvshow.feature.home.ui

import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.com.mptvshow.MPTvShowApplication
import com.example.com.mptvshow.R
import com.example.com.mptvshow.extensions.hide
import com.example.com.mptvshow.extensions.show
import com.example.com.mptvshow.feature.detail.ui.TvShowDetailFragment
import com.example.com.mptvshow.feature.home.presentation.ListTvShowPresenter
import com.example.com.mptvshow.feature.home.presentation.ListTvView
import com.example.com.mptvshow.feature.home.ui.adapter.ListTvShowAdapter
import com.example.com.mptvshow.feature.shared.domain.entities.Loader
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.shared.base.BaseFragment
import com.example.com.mptvshow.widget.EndlessRecyclerViewScrollListener
import kotlinx.android.synthetic.main.fragment_list_tv_show.*
import javax.inject.Inject


open class ListTvShowsFragment: BaseFragment(), ListTvShowAdapter.ListTvShowListenner , ListTvView {

    @Inject
    lateinit var presenter: ListTvShowPresenter

    private var items = ArrayList<Any>()
    private lateinit var listTvShowAdapter : ListTvShowAdapter
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
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_tv_show, container, false)
        MPTvShowApplication().get().getCoreComponent().inject(this)
        presenter.bind(this)

        insertLoader()

        bindListener()
        setupRecycler()
        return  view
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    private fun bindListener() {
        txtMessage.setOnClickListener {
            resetState()
        }
    }

    private fun setupRecycler() = with(recyclerView) {
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
        val linearLayoutManager = GridLayoutManager(context, 2)

        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadData(page)
            }
        }

        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))


        layoutManager = linearLayoutManager
        adapter = listTvShowAdapter
        addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    fun loadData(page: Int) {
        presenter.fetchMostPopularTvShow(getString(R.string.api_token), page)
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

    protected open fun resetState() {
        items.clear()

        insertLoader()
        listTvShowAdapter.notifyDataSetChanged()

        txtMessage.hide()
        recyclerView.show()

        endlessRecyclerViewScrollListener?.resetState()
        endlessRecyclerViewScrollListener?.onScrolled(recyclerView, 0, 0)
    }

    override fun showResult(result: ArrayList<Any>) {
        removeLoader()

        if(result.isEmpty()) return

        val size = items.size

        items.addAll(result)

        listTvShowAdapter.notifyItemRangeChanged(size, items.size)

        insertLoader()
    }

    override fun showError(throwable: Throwable) {
        txtMessage.show()
        recyclerView.hide()
    }

    override fun onCLickTvShow(tvShowItem: TvShowItem, imgCover: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mainListener?.openFragmentTransaction(TvShowDetailFragment.newInstance(tvShowItem, ViewCompat.getTransitionName(imgCover)), "", imgCover)
        else
            mainListener?.openFragment(TvShowDetailFragment.newInstance(tvShowItem), "")
    }
}