package com.alpha.f.view.scalerecyclerview

import android.support.v7.widget.RecyclerView

interface OnSelectedChangedListsner {
    fun onSelectedChanged(view: RecyclerView, oldPosition: Int, position: Int)
}