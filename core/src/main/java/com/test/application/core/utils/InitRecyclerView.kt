package com.test.application.core.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.init(
    adapter: RecyclerView.Adapter<*>,
    orientation: Int
) {
    this.adapter = adapter
    layoutManager = LinearLayoutManager(context, orientation, false)
}