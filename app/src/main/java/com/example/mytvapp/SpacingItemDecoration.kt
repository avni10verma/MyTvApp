package com.example.mytvapp

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpacingItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = space
            outRect.right = space
            outRect.top = space / 2 // Apply half of the space as top margin
            outRect.bottom = space / 2 // Apply half of the space as bottom margin
        }
}

