package com.rexleung.kotlin_scroll_together

import androidx.recyclerview.widget.RecyclerView

/**
 * Created by rexchung on 23/10/2023.
 */
class ItemViewMultiScrollListener(
    private val otherRecyclerView: RecyclerView
) : RecyclerView.OnScrollListener() {

    private var isDragging = true

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//        when (newState) {
//            RecyclerView.SCROLL_STATE_DRAGGING->{
//                isDragging = true
//            }
//            else->{
//                isDragging = false
//            }
//        }
//        Log.d(Constant.TAG, "isDragging $isDragging")
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (isDragging) {
//            Log.d(Constant.TAG, "TwoOneScrollListener: ${otherRecyclerView.width} ${recyclerView.width} : dx: $dx")
            try {
                val mine = recyclerView.getChildAt(0).width
                val target = otherRecyclerView.getChildAt(0).width
                val scroll = dx * target / mine

                otherRecyclerView.scrollBy(scroll, dy)
//                Log.d(Constant.TAG, "TwoOneScrollListener: ${otherRecyclerView.getChildAt(0).width} ${recyclerView.getChildAt(0).width}  dx: $dx scroll: $scroll")
            } catch (e: Exception) {

            }
        }
    }
}
