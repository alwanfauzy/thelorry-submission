package com.alwan.thelorryresto.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class VerticalMarginItemDecoration(private val height: Int, private val width: Int = 0) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0)
                top = height
            bottom = height
            left = width
            right = width
        }
    }
}