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
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
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
import javax.inject.Inject


open class ListTvShowsFragment: BaseFragment(), ListTvShowAdapter.ListTvShowListenner , ListTvView {

    @Inject
    lateinit var presenter: ListTvShowPresenter

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.message)
    lateinit var message: TextView

    private var items = ArrayList<Any>()
    private lateinit var adapter : ListTvShowAdapter
    private var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener? = null


    companion object {

        fun newInstance(): ListTvShowsFragment {
            return ListTvShowsFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        items.clear()
        adapter = ListTvShowAdapter(items, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_tv_show, container, false)
        MPTvShowApplication().get().getCoreComponent().inject(this)
        ButterKnife.bind(this, view)
        presenter.bind(this)

        insertLoader()
        setupRecycler()
        return  view
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }

    private fun setupRecycler() = with(recyclerView) {
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
        val oldState = endlessRecyclerViewScrollListener?.state

        val linearLayoutManager = GridLayoutManager(context, 2)

        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadData(page)
            }
        }

        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))

        endlessRecyclerViewScrollListener?.state = oldState

        layoutManager = linearLayoutManager
        adapter = this@ListTvShowsFragment.adapter
        addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    fun loadData(page: Int) {
        presenter.fetchMostPopularTvShow(getString(R.string.api_token), page)
    }

    private fun insertLoader() {
        items.add(Loader())
        adapter.notifyItemInserted(items.size - 1)
    }

    private fun removeLoader() {
        if (items.isEmpty())
            return

        val lastIndex = items.size - 1
        if (items[lastIndex] is Loader) {
            items.removeAt(lastIndex)

            adapter.notifyItemRemoved(lastIndex)
        }
    }

    protected open fun resetState() {
        items.clear()

        insertLoader()
        adapter.notifyDataSetChanged()

        message.hide()
        recyclerView.show()

        endlessRecyclerViewScrollListener?.resetState()
        endlessRecyclerViewScrollListener?.onScrolled(recyclerView, 0, 0)
    }

    override fun showResult(result: ArrayList<Any>) {
        removeLoader()

        if(result.isEmpty()) return

        val size = items.size

        items.addAll(result)

        adapter.notifyItemRangeChanged(size, items.size)

        insertLoader()
    }

    override fun showError(throwable: Throwable) {
        message.show()
        recyclerView.hide()
    }

    override fun onCLickTvShow(tvShowItem: TvShowItem, imgCover: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mainListener?.openFragmentTransaction(TvShowDetailFragment.newInstance(tvShowItem, ViewCompat.getTransitionName(imgCover)), "", imgCover)
        else
            mainListener?.openFragment(TvShowDetailFragment.newInstance(tvShowItem), "")
    }

    @OnClick(R.id.message)
    fun onClickTryAgain() {
        resetState()
    }
}