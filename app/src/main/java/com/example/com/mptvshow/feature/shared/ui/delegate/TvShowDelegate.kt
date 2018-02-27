package com.example.com.mptvshow.feature.shared.ui.delegate

import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.com.mptvshow.R
import com.example.com.mptvshow.extensions.inflate
import com.example.com.mptvshow.feature.shared.domain.entities.ListTvShowItem
import com.example.com.mptvshow.feature.shared.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.shared.ui.ListTvShowAdapter
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate

class TvShowDelegate constructor(val listGenericListener: ListTvShowAdapter.ListGenericListener): AbsListItemAdapterDelegate<ListTvShowItem, Any, TvShowDelegate.ViewHolder>() {


    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_delegate_tv_show)) {

        @BindView(R.id.imgCover)
        lateinit var imgCover: ImageView

        @BindView(R.id.txtTitle)
        lateinit var txtTitle: TextView

        @BindView(R.id.txtSubTitle)
        lateinit var subTitle: TextView

        init {
            ButterKnife.bind(this, itemView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun isForViewType(item: Any,items: List<Any>, position: Int): Boolean {
        return item is ListTvShowItem
    }

    override fun onBindViewHolder(item: ListTvShowItem, viewHolder: TvShowDelegate.ViewHolder, payloads: MutableList<Any>) {
        val context =  viewHolder.imgCover.context


        Glide.with(context)
                .load(context.getString(R.string.base_url_images, item.posterImage))
                .error(R.drawable.img_place_holder)
                .into(viewHolder.imgCover)

        viewHolder.txtTitle.text = item.title
        viewHolder.subTitle.text = item.voteAverage.toString()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            viewHolder.imgCover.transitionName = item.id.toString()

        viewHolder.itemView.setOnClickListener {
            listGenericListener.onCLickTvShow(item, viewHolder.imgCover)
        }
    }
}