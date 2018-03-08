package com.example.com.mptvshow.feature.shared.ui

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.com.mptvshow.R
import com.example.com.mptvshow.extensions.inflate
import com.example.com.mptvshow.feature.shared.domain.entities.Loader

import com.hannesdorfmann.adapterdelegates3.AbsListItemAdapterDelegate


class LoaderDelegate : AbsListItemAdapterDelegate<Loader, Any, LoaderDelegate.ViewHolder>() {

    inner class ViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_delegate_loader))

    override fun onCreateViewHolder(parent: ViewGroup) = ViewHolder(parent)

    override fun isForViewType(item: Any,items: List<Any>, position: Int): Boolean {
        return item is Loader
    }

    override fun onBindViewHolder(loader: Loader, viewHolder: ViewHolder, payloads: List<Any>) {}

}
