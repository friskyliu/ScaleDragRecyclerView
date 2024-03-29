package com.frisky.view.recycleview.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frisky.view.recyclerview.demo.R
import com.frisky.view.scalerecyclerview.CacheAdapter

class HoriSmallAdapter(val data: List<String>) : CacheAdapter<HoriSmallHolder>() {
    override fun onBindViewHolderNew(viewHolder: HoriSmallHolder, position: Int) {
        viewHolder.textView.text = data[position]
    }

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): HoriSmallHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.rv_item_h, container, false)
        return HoriSmallHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}