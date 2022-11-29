package com.frisky.view.recycleview.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frisky.view.recyclerview.demo.R
import com.frisky.view.scalerecyclerview.CacheAdapter

class HorizontalAdapter(val data: List<String>) : CacheAdapter<HorizontalHolder>() {
    override fun onBindViewHolderNew(viewHolder: HorizontalHolder, position: Int) {
        viewHolder.textView.text = data[position]
    }

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): HorizontalHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.rv_item_h, container, false)
        return HorizontalHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}