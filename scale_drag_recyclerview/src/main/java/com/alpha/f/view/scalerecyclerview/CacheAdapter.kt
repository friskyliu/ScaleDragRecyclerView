package com.alpha.f.view.scalerecyclerview

import android.support.v7.widget.RecyclerView
import java.util.*

abstract class CacheAdapter<VH : CacheHolder> : RecyclerView.Adapter<VH>() {
    private val cacheMap = WeakHashMap<VH, Unit>()
    final override fun onBindViewHolder(viewHolder: VH, position: Int) {
        viewHolder.pos = position
        cacheMap[viewHolder] = Unit
        onBindViewHolderNew(viewHolder, position)
    }

    override fun onViewRecycled(holder: VH) {
        super.onViewRecycled(holder)
        cacheMap.remove(holder)
    }

    fun setDistance(pos: Int, distanceToCenter: Float?, itemViewSize: Int) {
        cacheMap.keys.filter { it.pos == pos }.forEach { it.setDistance(distanceToCenter, itemViewSize) }
    }

    fun clearCache() {
        cacheMap.clear()
    }

    abstract fun onBindViewHolderNew(viewHolder: VH, position: Int)
}