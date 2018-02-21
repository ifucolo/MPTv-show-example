package com.example.com.mptvshow.feature.detail.ui

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.example.com.mptvshow.R
import com.example.com.mptvshow.feature.BaseFragment
import com.example.com.mptvshow.feature.list.domain.entities.TvShowItem
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.Glide

class TvShowDetailFragment: BaseFragment() {


    @BindView(R.id.imgHero)
    lateinit var imgHero: ImageView

    var tvShowItem: TvShowItem? = null

    companion object {

        var EXTRA_TRANSACTION_NAME = "transaction_name"
        var EXTRA_TV_SHOW = "tv_show"

        fun newInstance(tvShowItem: TvShowItem, transactionName: String): TvShowDetailFragment{
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_TV_SHOW, tvShowItem)
            bundle.putString(EXTRA_TRANSACTION_NAME, transactionName)

            val fragment = TvShowDetailFragment()
            fragment.arguments = bundle

            return fragment
        }

        fun newInstance(tvShowItem: TvShowItem): TvShowDetailFragment{
            val bundle = Bundle()
            bundle.putSerializable(EXTRA_TV_SHOW, tvShowItem)

            val fragment = TvShowDetailFragment()
            fragment.arguments = bundle

            return fragment
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tv_show_detail, container, false)

        ButterKnife.bind(this, view)

        tvShowItem = arguments?.getSerializable(EXTRA_TV_SHOW) as TvShowItem

        setUpTransition()

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            imgHero.transitionName = arguments?.getString(EXTRA_TRANSACTION_NAME)

        Glide.with(this)
                .load(context?.getString(R.string.base_url_images, tvShowItem?.posterImage))
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

    private fun setUpTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        }
    }
}