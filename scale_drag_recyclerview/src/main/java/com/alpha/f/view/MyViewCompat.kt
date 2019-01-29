package com.alpha.f.view

import android.os.Build
import android.view.View
import android.view.ViewTreeObserver

fun View.isLayouted(): Boolean {
    return width > 0 && height > 0
}

fun View.onGlobalLayout(runnable: Runnable) {
    if (isLayouted()) {
        runnable.run()
        return
    }

    val onGlobalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
        override fun onGlobalLayout() {
            runnable.run()
            if (Build.VERSION.SDK_INT >= 16) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
            } else {
                viewTreeObserver.removeGlobalOnLayoutListener(this)
            }
        }
    }

    viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener)
}