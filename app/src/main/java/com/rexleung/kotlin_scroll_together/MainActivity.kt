package com.rexleung.kotlin_scroll_together

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    var recyclerView1: RecyclerView? = null
    var recyclerView2: RecyclerView? = null

    val itemCenterDecorationOne = ItemViewOneAdapter.ItemCenterDecoration()

    private var enableSyncScroll = true

    private val itemViewMultiScrollListener: ItemViewMultiScrollListener by lazy {
        ItemViewMultiScrollListener(
            otherRecyclerView = recyclerView2!!,
        )
    }

    private val margin: Int by lazy {
        val widthParent = this@MainActivity.recyclerView1?.width!!
        widthParent / 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView1 = findViewById<RecyclerView>(R.id.recyclerView1).apply {
            adapter = ItemViewOneAdapter(
                listOf("1", "2", "3", "4", "5", "6", "7"),
            )
            addItemDecoration(itemCenterDecorationOne)
            layoutManager = ItemViewOneLayoutManager(context)
        }

        recyclerView2 = findViewById<RecyclerView>(R.id.recyclerView2).apply {
            adapter = ItemViewTwoAdapter(
                listOf("1", "2", "3", "4", "5", "6", "7"),
            )
            addItemDecoration(ItemViewTwoAdapter.ItemCenterDecoration())
            layoutManager = ItemViewTwoLayoutManager(context)
        }


        recyclerView1?.addOnScrollListener(itemViewMultiScrollListener)

        val snapHelper1 = PagerSnapHelper().apply {
            attachToRecyclerView(recyclerView1)
        }
        val snapHelper2 = PagerSnapHelper().apply {
            attachToRecyclerView(recyclerView2)
        }

        val snapOnScrollListener = SnapOnScrollListener(
            snapHelper1,
            SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE,
            object : OnSnapPositionChangeListener {
                override fun onSnapPositionChange(position: Int, dx: Int?, dy: Int?) {
                    Log.d("onSnapPositionChange", "$position $dx $dy")
                    if (enableSyncScroll) {
                        recyclerView2?.scrollToPosition(position)
                    }
                }
            },
        )
        recyclerView1?.addOnScrollListener(snapOnScrollListener)
    }
}
