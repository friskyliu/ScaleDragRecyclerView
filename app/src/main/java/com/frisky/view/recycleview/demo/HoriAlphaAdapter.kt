package com.frisky.view.recycleview.demo

import android.view.LayoutInflater
import android.view.ViewGroup
import com.frisky.view.recyclerview.demo.R
import com.frisky.view.scalerecyclerview.CacheAdapter

class HoriAlphaAdapter(val data: List<String>) : CacheAdapter<HoriAlphaHolder>() {
    override fun onBindViewHolderNew(viewHolder: HoriAlphaHolder, position: Int) {
        viewHolder.textView.text = data[position]
    }

    override fun onCreateViewHolder(container: ViewGroup, pos: Int): HoriAlphaHolder {
        val view = LayoutInflater.from(container.context).inflate(R.layout.rv_item_alpha_h, container, false)
        return HoriAlphaHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}