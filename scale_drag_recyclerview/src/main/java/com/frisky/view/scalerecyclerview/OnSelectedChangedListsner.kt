package com.frisky.view.scalerecyclerview

import androidx.recyclerview.widget.RecyclerView

interface OnSelectedChangedListsner {
    fun onSelectedChanged(view: RecyclerView, oldPosition: Int, position: Int)
}