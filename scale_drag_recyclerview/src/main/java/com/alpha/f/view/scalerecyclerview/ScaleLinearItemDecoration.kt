package com.alpha.f.view.scalerecyclerview

import android.graphics.Rect
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View

class ScaleLinearItemDecoration(private val viewSize: Int, private val ori: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val count = parent.adapter?.itemCount ?: return
        val pos = parent.getChildLayoutPosition(view)
        if (ori == LinearLayoutManager.HORIZONTAL) {
            when (pos) {
                0 -> {
                    outRect.left = (parent.measuredWidth - viewSize) / 2
                }
                count - 1 -> {
                    outRect.right = (parent.measuredWidth - viewSize) / 2
                }
            }
        } else {
            when (pos) {
                0 -> {
                    outRect.top = (parent.measuredHeight - viewSize) / 2
                }
                count - 1 -> {
                    outRect.bottom = (parent.measuredHeight - viewSize) / 2
                }
            }
        }
    }
}