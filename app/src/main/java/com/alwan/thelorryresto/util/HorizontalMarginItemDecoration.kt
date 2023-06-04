package com.alwan.thelorryresto.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HorizontalMarginItemDecoration(private val height: Int = 0, private val width: Int) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0)
                left = width * 2
            right =
                if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount?.minus(1))
                    width * 2
                else
                    width
            top = height
            bottom = height
        }
    }
}