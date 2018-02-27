package com.example.com.mptvshow.feature.shared.fragment

import android.os.Build
import android.os.Bundle
import android.support.v4.view.ViewCompat
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.com.mptvshow.MPTvShowApplication
import com.example.com.mptvshow.R
import com.example.com.mptvshow.extensions.hide
import com.example.com.mptvshow.extensions.show
import com.example.com.mptvshow.feature.detail.TvShowDetailFragment
import com.example.com.mptvshow.feature.shared.domain.entities.Loader
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.shared.presentation.BaseListShowTvPresenter
import com.example.com.mptvshow.feature.shared.presentation.BaseListShowTvView
import com.example.com.mptvshow.feature.shared.ui.ListTvShowAdapter
import com.example.com.mptvshow.widget.EndlessRecyclerViewScrollListener
import java.util.ArrayList
import javax.inject.Inject

abstract class BaseListFragment: BaseFragment(), BaseListShowTvView, ListTvShowAdapter.ListGenericListener {

    @Inject
    lateinit var presenter: BaseListShowTvPresenter

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.message)
    lateinit var message: TextView

    private var items: ArrayList<Any> =  ArrayList()
    private lateinit var listTvShowAdapter : ListTvShowAdapter
    private var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        items.clear()
        listTvShowAdapter = ListTvShowAdapter(items, this)

    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
    }


    protected fun initView(view: View, isDetail: Boolean) {

        ButterKnife.bind(this, view)
        MPTvShowApplication().get().getCoreComponent().inject(this)

        presenter.bind(this)

        insertLoader()
        setupRecycler(isDetail)
    }

    abstract fun loadData(page: Int)

    private fun setupRecycler(isDetail: Boolean) = with(recyclerView) {
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
        val oldState = endlessRecyclerViewScrollListener?.state

        val linearLayoutManager =
                if (isDetail)
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                else
                    GridLayoutManager(context, 2)

        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadData(page)
            }
        }

        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        if (!isDetail) addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))

        endlessRecyclerViewScrollListener?.state = oldState

        layoutManager = linearLayoutManager
        adapter = listTvShowAdapter
        addOnScrollListener(endlessRecyclerViewScrollListener)
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

        message.hide()
        recyclerView.show()

        endlessRecyclerViewScrollListener?.resetState()
        endlessRecyclerViewScrollListener?.onScrolled(recyclerView, 0, 0)
    }

    override fun onCLickTvShow(tvShowItem: TvShowItem, imgCover: ImageView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            mainListener?.openFragmentTransaction(TvShowDetailFragment.newInstance(tvShowItem, ViewCompat.getTransitionName(imgCover)), "", imgCover)
        else
            mainListener?.openFragment(TvShowDetailFragment.newInstance(tvShowItem), "")

    }

    override fun showResult(result: ArrayList<Any>) {
        removeLoader()

        if(result.isEmpty())
            return

        val size = items.size

        items.addAll(result)

        listTvShowAdapter.notifyItemRangeChanged(size, items.size)

        insertLoader()
    }

    override fun showError(throwable: Throwable) {
        message.show()
        recyclerView.hide()
    }

    @OnClick(R.id.message)
    fun onClickTryAgain() {
        resetState()
    }
}