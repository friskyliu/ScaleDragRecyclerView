package com.alpha.f.view.recyclerview.demo

import android.view.View
import android.view.ViewConfiguration
import android.widget.TextView
import com.alpha.f.view.scalerecyclerview.CacheHolder

class HoriSmallHolder(view: View) : CacheHolder(view) {
    companion object {
        const val MIN_SCALE = 0.5F
    }

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
                scale = 1.0F - MIN_SCALE * tmp
            }
            distanceToCenter >= scaledTouchSlop -> {
                val tmp = 1 - distanceToCenter / itemViewSize.toFloat()
                trans = maxTrans * (1 - tmp)
                scale = 1.0F - MIN_SCALE * tmp
            }
            else -> {
                scale = MIN_SCALE
            }
        }

        textView.translationX = trans
        textView.scaleX = scale
        textView.scaleY = scale
    }
}