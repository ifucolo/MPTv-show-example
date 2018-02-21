package com.example.com.mptvshow.feature.list.ui.delegate

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.example.com.mptvshow.R
import com.example.com.mptvshow.extensions.inflate
import com.example.com.mptvshow.feature.list.domain.entities.TvShowItem
import com.example.com.mptvshow.feature.list.ui.ListTvShowAdapter
import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate

class TvShowDelegate constructor(val listGenericListener: ListTvShowAdapter.ListGenericListener): AbsListItemAdapterDelegate<TvShowItem, Any, TvShowDelegate.ViewHolder>() {


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
        return item is TvShowItem
    }

    override fun onBindViewHolder(item: TvShowItem, viewHolder: TvShowDelegate.ViewHolder, payloads: MutableList<Any>) {
        val context =  viewHolder.imgCover.context

        Glide.with(context)
                .load(context.getString(R.string.base_url_images, item.posterImage))
                .error(R.drawable.img_place_holder)
                .into(viewHolder.imgCover)

        viewHolder.txtTitle.text = item.title
        viewHolder.subTitle.text = item.voteAverage.toString()

        viewHolder.itemView.setOnClickListener {
            listGenericListener.onCLickTvShow(item, viewHolder.imgCover)
        }
    }
}