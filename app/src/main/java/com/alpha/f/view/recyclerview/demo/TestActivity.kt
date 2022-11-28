package com.alpha.f.view.recyclerview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import com.alpha.f.view.scalerecyclerview.OnSelectedChangedListsner
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity(), OnSelectedChangedListsner {
    companion object {
        const val TAG = "TestActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val list = getTestData()

        test_rv.adapter = HorizontalAdapter(list)
        test_rv.selectPosition(10)
        test_rv.onSelectedChangedListsner = this

        test_rv_alpha.adapter = HoriAlphaAdapter(list)
        test_rv_alpha.selectPosition(20)
        test_rv_alpha.onSelectedChangedListsner = this

        test_rv_small.adapter = HoriSmallAdapter(list)
        test_rv_small.selectPosition(30)
        test_rv_small.onSelectedChangedListsner = this

        test_rv_v.adapter = VerticalAdapter(list)
        test_rv_v.selectPosition(40)
        test_rv_v.onSelectedChangedListsner = this
    }

    override fun onDestroy() {
        super.onDestroy()
        test_rv.onSelectedChangedListsner = null
        test_rv_alpha.onSelectedChangedListsner = null
        test_rv_small.onSelectedChangedListsner = null
        test_rv_v.onSelectedChangedListsner = null
    }

    override fun onSelectedChanged(view: RecyclerView, oldPosition: Int, position: Int) {
        Log.e(TAG, "onSelectedChanged:$oldPosition -> $position")
    }

    private fun getTestData(): List<String> {
        val list = ArrayList<String>()
        (0 until 100).map { it.toString() }.forEach { list.add(it) }
        return list
    }
}

