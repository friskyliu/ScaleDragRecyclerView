package com.frisky.view.recycleview.demo

import android.view.View
import android.widget.TextView
import com.frisky.view.recyclerview.demo.R
import com.frisky.view.scalerecyclerview.CacheHolder

class HoriSmallHolder(view: View) : CacheHolder(view) {
    companion object {
        const val MIN_SCALE = 0.5F
    }

    val textView = view.findViewById<TextView>(R.id.round_item)

    override fun setDistance(distanceToCenter: Float?, itemViewSize: Int) {
        if (distanceToCenter == null) {
            return
        }

        val maxTrans = itemViewSize / 6F
        val trans: Float
        var scale = 1F
        when {
            distanceToCenter <= -itemViewSize -> {
                trans = maxTrans
            }
            distanceToCenter >= itemViewSize -> {
                trans = -maxTrans
            }
            else -> {
                val beforeCenter = distanceToCenter < 0F
                val absDistanceToCenter = Math.abs(distanceToCenter)
                val tmp = 1 - absDistanceToCenter / itemViewSize.toFloat()
                trans = if (beforeCenter) {
                    maxTrans * (1 - tmp)
                } else {
                    -maxTrans * (1 - tmp)
                }
                scale = 1.0F - MIN_SCALE * tmp
            }
        }

        textView.translationX = trans
        textView.scaleX = scale
        textView.scaleY = scale
    }
}