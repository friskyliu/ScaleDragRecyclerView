package com.alpha.f.view.scalerecyclerview

import android.support.v7.widget.RecyclerView
import android.view.View

open class CacheHolder(view: View): RecyclerView.ViewHolder(view) {
    var pos = -1

    open fun setDistance(distanceToCenter: Float?, itemViewSize:Int) {

    }
}