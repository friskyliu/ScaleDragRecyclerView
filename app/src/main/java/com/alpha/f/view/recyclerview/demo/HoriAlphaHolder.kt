package com.alpha.f.view.recyclerview.demo

import android.content.res.Resources
import android.view.View
import android.widget.TextView
import com.alpha.f.view.scalerecyclerview.CacheHolder

class HoriAlphaHolder(view: View) : CacheHolder(view) {
    companion object {
        const val MAX_SCALE = 1.5F
    }

    val textView = view.findViewById<TextView>(R.id.round_item)
    private val halfWidth = Resources.getSystem().displayMetrics.widthPixels / 2.0F

    override fun setDistance(distanceToCenter: Float?, itemViewSize: Int) {
        if (distanceToCenter == null) {
            return
        }

        val maxTrans = itemViewSize / 6F
        val trans: Float
        var scale = 1F
        val absDistanceToCenter = Math.abs(distanceToCenter)
        val alpha = 1.0f - absDistanceToCenter / halfWidth
        when {
            distanceToCenter <= -itemViewSize -> {
                trans = -maxTrans
            }
            distanceToCenter >= itemViewSize -> {
                trans = maxTrans
            }
            else -> {
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

        textView.translationX = trans
        textView.scaleX = scale
        textView.scaleY = scale
        textView.alpha = alpha
    }
}