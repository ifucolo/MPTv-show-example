package com.example.com.mptvshow.feature.detail.ui

import android.os.Build
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.view.ViewCompat
import android.support.v7.widget.*
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.example.com.mptvshow.R
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.Glide
import com.example.com.mptvshow.MPTvShowApplication
import com.example.com.mptvshow.extensions.hide
import com.example.com.mptvshow.extensions.show
import com.example.com.mptvshow.feature.detail.presentation.TvShowDetailPresenter
import com.example.com.mptvshow.feature.detail.presentation.TvShowDetailView
import com.example.com.mptvshow.feature.home.ui.adapter.ListTvShowAdapter
import com.example.com.mptvshow.feature.main.MainActivity
import com.example.com.mptvshow.feature.shared.domain.entities.Loader
import com.example.com.mptvshow.feature.shared.base.BaseFragment
import com.example.com.mptvshow.widget.EndlessRecyclerViewScrollListener
import javax.inject.Inject

class TvShowDetailFragment: BaseFragment(), TvShowDetailView, TvShowDetailAdapter.TvShowDetailAdapterListener {

    @BindView(R.id.imgHero)
    lateinit var imgHero: ImageView

    @BindView(R.id.txtAverage)
    lateinit var txtAverage: TextView

    @BindView(R.id.txtDescription)
    lateinit var txtDescription: TextView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.collapsingToolbar)
    lateinit var collapsingToolbar: CollapsingToolbarLayout

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.message)
    lateinit var message: TextView

    @Inject
    lateinit var presenter: TvShowDetailPresenter

    private var items: java.util.ArrayList<Any> = java.util.ArrayList()
    private lateinit var adapter : TvShowDetailAdapter
    private var endlessRecyclerViewScrollListener: EndlessRecyclerViewScrollListener? = null

    private var tvShowItem: TvShowItem? = null

    companion object {

        var EXTRA_TRANSACTION_NAME = "transaction_name"
        var EXTRA_TV_SHOW = "tv_show"

        fun newInstance(tvShowItem: TvShowItem, transactionName: String): TvShowDetailFragment {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_TV_SHOW, tvShowItem)
            bundle.putString(EXTRA_TRANSACTION_NAME, transactionName)

            val fragment = TvShowDetailFragment()
            fragment.arguments = bundle

            return fragment
        }

        fun newInstance(tvShowItem: TvShowItem): TvShowDetailFragment {
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_TV_SHOW, tvShowItem)

            val fragment = TvShowDetailFragment()
            fragment.arguments = bundle

            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        items.clear()
        adapter = TvShowDetailAdapter(items, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tv_show_detail, container, false)
        ButterKnife.bind(this, view)
        MPTvShowApplication().get().getCoreComponent().inject(this)

        presenter.bind(this)

        setData()

        insertLoader()
        setupRecycler()

        return view
    }

    override fun onDestroy() {
        presenter.unbind()
        super.onDestroy()
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

    private fun setData() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
            imgHero.transitionName = arguments?.getString(EXTRA_TRANSACTION_NAME)
        }

        tvShowItem = arguments?.getSerializable(EXTRA_TV_SHOW) as TvShowItem

        (activity as MainActivity).setSupportActionBar(toolbar)
        toolbar.navigationIcon = resources.getDrawable(R.drawable.ic_back)
        toolbar.setNavigationOnClickListener({ (activity as MainActivity).onBackPressed() })

        txtAverage.text = tvShowItem?.voteAverage.toString()

        collapsingToolbar.title = tvShowItem?.title
        txtDescription.text = tvShowItem?.overview

        Glide.with(this)
                .load(context?.getString(R.string.base_url_images, tvShowItem?.posterImage)?: "")
                .centerCrop()
                .dontAnimate()
                .listener(object : RequestListener<String, GlideDrawable> {
                    override fun onException(e: Exception, model: String, target: com.bumptech.glide.request.target.Target<GlideDrawable>, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: GlideDrawable, model: String, target: com.bumptech.glide.request.target.Target<GlideDrawable>, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        startPostponedEnterTransition()
                        return false
                    }
                })
                .into(imgHero)
    }

    private fun setupRecycler() = with(recyclerView) {
        ViewCompat.setNestedScrollingEnabled(recyclerView, false)
        val oldState = endlessRecyclerViewScrollListener?.state

        val linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        endlessRecyclerViewScrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
                loadData(page)
            }
        }

        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))

        endlessRecyclerViewScrollListener?.state = oldState

        layoutManager = linearLayoutManager
        adapter = this@TvShowDetailFragment.adapter
        addOnScrollListener(endlessRecyclerViewScrollListener)
    }

    fun loadData(page: Int) {
        presenter.fetchSimilarTvShows(tvShowItem?.id?: 0, getString(R.string.api_token), page)
    }

    private fun resetState() {
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