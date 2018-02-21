package com.example.com.mptvshow.widget

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager

abstract class EndlessRecyclerViewScrollListener constructor(val layoutManager: RecyclerView.LayoutManager) : RecyclerView.OnScrollListener() {

    // The minimum amountNormalized of items to have below your current scroll position
    // before loading more.
    private var visibleThreshold = 3
    // The current offset index of data you have loaded
    private var currentPage = 0
    // The total number of items in the dataset after the last load
    private var previousTotalItemCount = 0
    // True if we are still waiting for the last set of data to load.
    private var loading = true
    // Sets the starting page index
    private val startingPageIndex = 0

    private var completedLoading = true

    init{
        if (layoutManager is StaggeredGridLayoutManager) visibleThreshold *= layoutManager.spanCount
    }

    fun getLastVisibleItem(lastVisibleItemPositions: IntArray): Int {
        var maxSize = 0
        for (i in lastVisibleItemPositions.indices) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i]
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i]
            }
        }
        return maxSize
    }

    override fun onScrolled(view: RecyclerView, dx: Int, dy: Int) {
        var lastVisibleItemPosition = 0
        val totalItemCount = layoutManager.getItemCount()

        if (layoutManager is StaggeredGridLayoutManager) {
            val lastVisibleItemPositions = (layoutManager as StaggeredGridLayoutManager).findLastVisibleItemPositions(null)
            // get maximum element within the list
            lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions)
        } else if (layoutManager is GridLayoutManager) {
            lastVisibleItemPosition = (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
        } else if (layoutManager is LinearLayoutManager) {
            lastVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
        }

        // If it’s still loading, we check to see if the dataset count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && totalItemCount > previousTotalItemCount) {
            loading = false
            previousTotalItemCount = totalItemCount
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
            currentPage++
            completedLoading = false
            onLoadMore(currentPage, totalItemCount, view)
            loading = true
        }



    }

    fun resetState() {
        resetState(startingPageIndex)
    }

    fun checkCompletedLoading(): Boolean {
        return if (!completedLoading) {
            completedLoading = true
            resetState(currentPage - 1)

            false
        } else
            true
    }

    fun setCompletedLoading() {
        this.completedLoading = true
    }

    fun resetState(page: Int) {
        this.currentPage = page
        this.previousTotalItemCount = 0
        this.loading = true
    }

    fun getState()= Bundle().apply {
        putInt("currentPage", currentPage)
        putInt("previousTotalItemCount", previousTotalItemCount)
        putBoolean("loading", loading)
    }

    fun getCurrentPage(): Int {
        return currentPage
    }

    fun setState(bundle: Bundle?) {
        if (bundle != null) {
            currentPage = bundle.getInt("currentPage", startingPageIndex)
            previousTotalItemCount = bundle.getInt("previousTotalItemCount", 0)
            loading = bundle.getBoolean("loading", true)
        }
    }

    // Defines the process for actually loading more data based on page
    abstract fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView)

}// Call this method whenever performing new searches