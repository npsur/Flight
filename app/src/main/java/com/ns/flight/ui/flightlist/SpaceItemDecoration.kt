package com.ns.flight.ui.flightlist

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.core.content.res.getDimensionPixelSizeOrThrow
import androidx.recyclerview.widget.RecyclerView
import com.ns.flight.R

/**
 * Add horizontal margins to all cells within the recycler view.
 */
class SpaceItemDecoration(context: Context) : RecyclerView.ItemDecoration() {
    private val padding: Int

    init {
        val a = context.obtainStyledAttributes(
            R.style.SpaceDecoration,
            R.styleable.SpaceDecoration
        )

        padding = a.getDimensionPixelSizeOrThrow(R.styleable.SpaceDecoration_android_padding)
        a.recycle()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.left = padding
        outRect.right = padding
    }
}