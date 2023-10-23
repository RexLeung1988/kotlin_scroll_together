package com.rexleung.kotlin_scroll_together

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by rexchung on 23/10/2023.
 */
class ItemViewTwoLayoutManager(
    val context: Context,
) : LinearLayoutManager(context, HORIZONTAL, false) {

    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        return super.checkLayoutParams(lp)
    }

    override fun onLayoutCompleted(state: RecyclerView.State?) =
        super.onLayoutCompleted(state).also { onLayoutChangeChildView() }

    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler,
        state: RecyclerView.State,
    ) = super.scrollHorizontallyBy(dx, recycler, state).also {
        if (orientation == HORIZONTAL) onLayoutChangeChildView()
    }

    private fun onLayoutChangeChildView() {
//        val containerCenter = width / 2f
//        Log.d(Constant.TAG, "ItemViewTwoLayoutManager: width: $width")
    }
}
