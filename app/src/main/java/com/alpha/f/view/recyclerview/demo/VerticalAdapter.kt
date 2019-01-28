package com.alpha.f.view.recyclerview.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.alpha.f.view.scalerecyclerview.CacheAdapter

class VerticalAdapter(val data: List<String>) : CacheAdapter<VerticalHolder>() {
    override fun onBindViewHolderNew(viewHolder: VerticalHolder, position: Int) {
        viewHolder.textView.text = data[position]
    }

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): VerticalHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.rv_item_v, container, false)
        return VerticalHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}