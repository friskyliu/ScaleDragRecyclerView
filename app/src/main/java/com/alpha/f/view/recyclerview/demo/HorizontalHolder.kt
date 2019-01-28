package com.alpha.f.view.recyclerview.demo

import android.view.View
import android.view.ViewConfiguration
import android.widget.TextView
import com.alpha.f.view.scalerecyclerview.CacheHolder

class HorizontalHolder(view: View) : CacheHolder(view) {
    val textView = view.findViewById<TextView>(R.id.round_item)
    private val scaledTouchSlop = ViewConfiguration.get(view.context).scaledTouchSlop.toFloat()

    override fun setDistance(distanceToCenter: Float?, itemViewSize: Int) {
        if (distanceToCenter == null) {
            return
        }

        val maxTrans = itemViewSize / 6F
        var trans = 0F
        var scale = 1F
        when {
            distanceToCenter <= -itemViewSize -> {
                trans = -maxTrans
            }
            distanceToCenter >= itemViewSize -> {
                trans = maxTrans
            }
            distanceToCenter <= -scaledTouchSlop -> {
                val tmp = 1 + distanceToCenter / itemViewSize.toFloat()
                trans = -maxTrans * (1 - tmp)
                scale = 1.0F + (TestActivity.MAX_SCALE - 1.0F) * tmp
            }
            distanceToCenter >= scaledTouchSlop -> {
                val tmp = 1 - distanceToCenter / itemViewSize.toFloat()
                trans = maxTrans * (1 - tmp)
                scale = 1.0F + (TestActivity.MAX_SCALE - 1.0F) * tmp
            }
            else -> {
                scale = TestActivity.MAX_SCALE
            }
        }

        textView.translationX = trans
        textView.scaleX = scale
        textView.scaleY = scale
    }
}