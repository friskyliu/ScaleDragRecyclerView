package com.frisky.view.scalerecyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.View

open class CacheHolder(view: View) : RecyclerView.ViewHolder(view) {
    var pos = -1
        internal set

    open fun setDistance(distanceToCenter: Float?, itemViewSize: Int) {

    }
}