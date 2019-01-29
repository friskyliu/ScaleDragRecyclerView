package com.alpha.f.view.scalerecyclerview

import android.content.Context
import android.support.v7.widget.CenterLinearSnapHelper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import com.alpha.f.view.onGlobalLayout
import kotlin.math.roundToInt

class ScaleLinearRecyclerView : RecyclerView {
    private lateinit var linearLayoutManager: LinearLayoutManager
    private var orientation = LinearLayoutManager.HORIZONTAL
    private val snapHelper = CenterLinearSnapHelper()
    private var itemViewSize: Float = 0F
    private var selectedPosition = 0
    var onSelectedChangedListsner: OnSelectedChangedListsner? = null

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init(context, attrs)
    }

    fun selectPosition(pos: Int) {
        onGlobalLayout(Runnable {
            val offset = if (orientation == LinearLayoutManager.HORIZONTAL) {
                ((width - itemViewSize) / 2F).roundToInt()
            } else {
                ((height - itemViewSize) / 2F).roundToInt()
            }
            selectedPosition = pos
            linearLayoutManager.scrollToPositionWithOffset(pos, offset)
        })
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        loadAttrs(context, attrs)
        snapHelper.attachToRecyclerView(this)
        addItemDecoration(ScaleLinearItemDecoration(itemViewSize.roundToInt(), orientation))
        linearLayoutManager = object : LinearLayoutManager(context, orientation, false) {
            override fun onLayoutCompleted(state: RecyclerView.State?) {
                super.onLayoutCompleted(state)
                notifyScrolled()
            }
        }
        layoutManager = linearLayoutManager

        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                notifyScrolled(newState == RecyclerView.SCROLL_STATE_IDLE)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                notifyScrolled()
            }
        })
    }

    private fun loadAttrs(ctx: Context, attrs: AttributeSet?) {
        if (attrs == null) {
            orientation = LinearLayoutManager.HORIZONTAL
            itemViewSize = 120F
            return
        }

        val typedArray = ctx.obtainStyledAttributes(attrs, R.styleable.ScaleLinearRecyclerView)
        orientation = typedArray.getInt(R.styleable.ScaleLinearRecyclerView_orientation, LinearLayoutManager.HORIZONTAL)
        itemViewSize = typedArray.getDimension(R.styleable.ScaleLinearRecyclerView_item_size, 120F)
        typedArray.recycle()
    }

    private fun notifyScrolled(needFetchPos: Boolean = false) {
        val firstPos = linearLayoutManager.findFirstVisibleItemPosition()
        val lastPos = linearLayoutManager.findLastVisibleItemPosition()
        (firstPos..lastPos).forEach { pos ->
            val view = linearLayoutManager.findViewByPosition(pos)
            if (view != null) {
                val distance = if (orientation == LinearLayoutManager.HORIZONTAL) {
                    view.left - ((width - itemViewSize) / 2)
                } else {
                    view.top - ((height - itemViewSize) / 2)
                }
                (adapter as CacheAdapter).setDistance(pos, distance, itemViewSize.roundToInt())
            }
        }

        if (needFetchPos) {
            val centerView = snapHelper.findSnapView(linearLayoutManager)
            val pos = if (centerView == null) {
                -1
            } else {
                getChildLayoutPosition(centerView)
            }
            if (pos != selectedPosition) {
                val old = selectedPosition
                selectedPosition = pos
                onSelectedChangedListsner?.onSelectedChanged(this, old, pos)
            }
        }
    }
}