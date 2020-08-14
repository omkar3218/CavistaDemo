package com.omkar.cavistademo.ui.custom

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(
    private var mLinearLayoutManager: GridLayoutManager
) : RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var currentPage = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        val visibleThreshold = 1
        if (!loading
            && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold
        ) {
            currentPage++
            onLoadMore(currentPage)
            loading = true
        }
    }

    protected abstract fun onLoadMore(current_page: Int)

}