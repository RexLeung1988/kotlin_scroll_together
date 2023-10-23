package com.rexleung.kotlin_scroll_together

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

/**
 * Created by rexchung on 23/10/2023.
 */
class ItemViewOneLayoutManager(
    val context: Context,
) : LinearLayoutManager(context, HORIZONTAL, false) {
    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
        lp?.width = width * 159 / 375
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
        val containerCenter = width / 2f
//        Log.d(Constant.TAG, "ItemViewOneLayoutManager: width: $width")

        for (i in 0 until childCount) {
            val child = getChildAt(i) ?: return
            if (i == 0) {
//                Log.d(Constant.TAG, "ItemViewOneLayoutManager: width: $width child.width: ${child.width}")
            }

            val childCenter = child.x + (child.width / 2)
            val distanceScale = (containerCenter - abs(childCenter - containerCenter)) / containerCenter

            val topMoveMax = (height * 0.3f)
            child.y = -((1 - distanceScale) * topMoveMax)
        }
    }
}
