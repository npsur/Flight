package com.ns.flight.ui.flightlist

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.SparseArray
import android.view.View
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.graphics.withTranslation
import androidx.core.util.containsKey
import androidx.core.view.forEach
import androidx.recyclerview.widget.RecyclerView
import com.ns.flight.R
import com.ns.flight.util.newStaticLayout
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

class DateSeparatorItemDecoration(
    context: Context,
    map: Map<LocalDate, Int>
) : RecyclerView.ItemDecoration() {
    private val padding: Int
    private val offset: Int
    private val headers: SparseArray<StaticLayout>
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    init {
        val a = context.obtainStyledAttributes(
            R.style.DateSeparatorDecoration,
            R.styleable.DateSeparatorDecoration
        )

        padding = context.resources.getDimensionPixelSize(R.dimen.normal_padding)
        offset = context.resources.getDimensionPixelSize(R.dimen.small_padding)
        headers = measureLayouts(map)
        textPaint.apply {
            color = a.getColorOrThrow(R.styleable.DateSeparatorDecoration_android_textColor)
            textSize = a.getDimensionOrThrow(R.styleable.DateSeparatorDecoration_android_textSize)
            alpha = 127
        }

        a.recycle()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        outRect.top = if (headers.containsKey(position)) padding * 3 else padding
        outRect.bottom = if (position == state.itemCount - 1) padding else 0
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val layoutManager = parent.layoutManager ?: return

        parent.forEach { cell ->
            if (cell.top < parent.height && cell.bottom > 0) {
                val layout = headers[parent.getChildAdapterPosition(cell)]
                if (layout != null) {
                    val dx = padding.toFloat()
                    val dy = layoutManager.getDecoratedTop(cell).toFloat() + layout.height +
                            (padding * 2) - offset

                    canvas.withTranslation(dx, dy) {
                        layout.draw(this)
                    }
                }
            }
        }
    }

    private fun measureLayouts(
        map: Map<LocalDate, Int>
    ): SparseArray<StaticLayout> {
        val array = SparseArray<StaticLayout>()
        map.forEach { (date, index) ->
            val text = DateTimeFormatter.ofPattern("E',' dd MMMM")
                .format(date)
                .toUpperCase(Locale.ENGLISH)

            val layout = newStaticLayout(
                text,
                textPaint,
                300,
                Layout.Alignment.ALIGN_NORMAL,
                1f,
                0f,
                false
            )

            array.put(index, layout)
        }

        return array
    }
}

