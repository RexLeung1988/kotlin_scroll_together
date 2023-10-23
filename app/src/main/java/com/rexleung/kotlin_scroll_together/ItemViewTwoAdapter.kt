package com.rexleung.kotlin_scroll_together

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by rexchung on 23/10/2023.
 */
class ItemViewTwoAdapter(private val dataSet: List<String>) :
    RecyclerView.Adapter<ItemViewTwoAdapter.ViewHolder>() {

    class ItemCenterDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            if (view == null) return

            val widthParent = parent.width
            val margin = widthParent * 20 / 375

//            Log.d("", "Decoration $widthParent $margin")
            outRect.left = margin
            outRect.right = margin
        }
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            textView = view.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.view_item_two, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textView.text = "$position"
    }

    override fun getItemCount() = dataSet.size
}
