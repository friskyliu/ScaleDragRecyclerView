package com.alpha.f.view.scalerecyclerview

import android.content.Context
import android.support.v7.widget.CenterLinearSnapHelper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import kotlin.math.roundToInt

class ScaleLinearRecyclerView : RecyclerView {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var orientation = LinearLayoutManager.HORIZONTAL
    private val snapHelper = CenterLinearSnapHelper()
    private var itemSize: Float = 0F

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        loadAttrs(context, attrs)
        snapHelper.attachToRecyclerView(this)
        addItemDecoration(ScaleLinearItemDecoration(itemSize.roundToInt(), orientation))
        linearLayoutManager = object : LinearLayoutManager(context, orientation, false) {
            override fun onLayoutCompleted(state: RecyclerView.State?) {
                super.onLayoutCompleted(state)
                notifyItemScaled()
            }
        }
        layoutManager = linearLayoutManager

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                notifyItemScaled()
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                notifyItemScaled()
            }
        })
    }

    private fun loadAttrs(ctx: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            orientation = LinearLayoutManager.HORIZONTAL
            itemSize = 120F
            return
        }

        val typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.ScaleLinearRecyclerView)
        orientation = typedArray.getInt(R.styleable.ScaleLinearRecyclerView_orientation, LinearLayoutManager.HORIZONTAL)
        itemSize = typedArray.getDimension(R.styleable.ScaleLinearRecyclerView_item_size, 120F)
        typedArray.recycle()
    }

    private fun notifyItemScaled() {
        val firstPos = linearLayoutManager.findFirstVisibleItemPosition()
        val lastPos = linearLayoutManager.findLastVisibleItemPosition()
        (firstPos..lastPos).forEach { pos ->
            val view = linearLayoutManager.findViewByPosition(pos)
            if (view != null) {
                val distance = if (orientation == LinearLayoutManager.HORIZONTAL) {
                    view.left - ((width - itemSize) / 2)
                } else {
                    view.top - ((height - itemSize) / 2)
                }
                (adapter as CacheAdapter).setDistance(pos, distance, itemSize.roundToInt())
            }
        }
    }
}