package com.alpha.f.view.recyclerview.demo

import android.view.View
import android.widget.TextView
import com.alpha.f.view.scalerecyclerview.CacheHolder

class VerticalHolder(view: View) : CacheHolder(view) {
    companion object {
        const val MAX_SCALE = 1.5F
    }

    val textView = view.findViewById<TextView>(R.id.round_item)

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
            else -> {
                val absDistanceToCenter = Math.abs(distanceToCenter)
                val beforeCenter = distanceToCenter < 0F
                val tmp = 1 - absDistanceToCenter / itemViewSize.toFloat()
                trans = if (beforeCenter) {
                    -maxTrans * (1 - tmp)
                } else {
                    maxTrans * (1 - tmp)
                }
                scale = 1.0F + (MAX_SCALE - 1.0F) * tmp
            }
        }

        textView.translationY = trans
        textView.scaleX = scale
        textView.scaleY = scale
    }
}