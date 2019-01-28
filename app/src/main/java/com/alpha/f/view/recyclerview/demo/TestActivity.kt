package com.alpha.f.view.recyclerview.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_test.*


class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val list = getTestData()

        test_rv.adapter = HorizontalAdapter(list)
        test_rv1.adapter = VerticalAdapter(list)
    }

    private fun getTestData(): List<String> {
        val list = ArrayList<String>()
        (0 until 100).map { it.toString() }.forEach { list.add(it) }
        return list
    }

    companion object {
        const val MAX_SCALE = 1.5F
    }
}

