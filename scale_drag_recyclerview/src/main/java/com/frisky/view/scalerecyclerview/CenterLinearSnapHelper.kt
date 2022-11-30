package com.frisky.view.scalerecyclerview

import android.view.View
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView

class CenterLinearSnapHelper : LinearSnapHelper() {
    private var mVerticalHelper: OrientationHelper? = null
    private var mHorizontalHelper: OrientationHelper? = null

    override fun findSnapView(layoutManager: RecyclerView.LayoutManager): View? {
        return if (layoutManager.canScrollVertically()) {
            findCenterView(layoutManager, getVerticalHelper(layoutManager))
        } else if (layoutManager.canScrollHorizontally()) {
            findCenterView(layoutManager, getHorizontalHelper(layoutManager))
        } else {
            null
        }
    }

    private fun findCenterView(layoutManager: RecyclerView.LayoutManager, helper: OrientationHelper): View? {
        val childCount = layoutManager.childCount
        if (childCount == 0) {
            return null
        } else {
            var closestChild: View? = null
            val center = if (layoutManager.clipToPadding) {
                helper.startAfterPadding + helper.totalSpace / 2
            } else {
                helper.end / 2
            }
            var absClosest = Int.MAX_VALUE
            for (i in 0 until childCount) {
                val child = layoutManager.getChildAt(i) ?: continue

                val params = child.layoutParams as RecyclerView.LayoutParams
                val childCenter = if (layoutManager.canScrollVertically()) {
                    helper.getDecoratedStart(child) + layoutManager.getTopDecorationHeight(child) + params.topMargin + child.measuredHeight / 2
                } else {
                    helper.getDecoratedStart(child) + layoutManager.getLeftDecorationWidth(child) + params.leftMargin + child.measuredWidth / 2
                }

                val absDistance = Math.abs(childCenter - center)
                if (absDistance < absClosest) {
                    absClosest = absDistance
                    closestChild = child
                }
            }
            return closestChild
        }
    }

    private fun getVerticalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (this.mVerticalHelper == null || this.mVerticalHelper!!.layoutManager !== layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager)
        }
        return this.mVerticalHelper!!
    }

    private fun getHorizontalHelper(layoutManager: RecyclerView.LayoutManager): OrientationHelper {
        if (this.mHorizontalHelper == null || this.mHorizontalHelper!!.layoutManager !== layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager)
        }
        return this.mHorizontalHelper!!
    }
}