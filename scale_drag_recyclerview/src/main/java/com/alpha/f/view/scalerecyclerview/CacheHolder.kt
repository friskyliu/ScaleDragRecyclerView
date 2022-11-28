package com.alpha.f.view.scalerecyclerview

import androidx.recyclerview.widget.RecyclerView
import android.view.View

open class CacheHolder(view: View): RecyclerView.ViewHolder(view) {
    internal var pos = -1

    open fun setDistance(distanceToCenter: Float?, itemViewSize:Int) {

    }
}