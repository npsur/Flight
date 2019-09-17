package com.ns.flight.ui.flightlist

import android.content.Context
import android.graphics.Canvas
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.getColorOrThrow
import androidx.core.content.res.getDimensionOrThrow
import androidx.core.content.res.getDrawableOrThrow
import androidx.core.graphics.withTranslation
import com.ns.flight.R

class PlanePathView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0
) : View(context, attrs, defStyleAttrs) {
    private val dotPaint: Paint
    private val plane: Drawable
    private val radius: Float
    private val planePathPaint: Paint
    private val planePath: Path

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.PlanePathView,
            defStyleAttrs,
            R.style.PlanePath)

        val paintColor = a.getColorOrThrow(R.styleable.PlanePathView_android_color)

        dotPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = paintColor
        }

        planePathPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = paintColor
            style = Paint.Style.STROKE
            strokeWidth = 4f
            val dashGap = a.getDimensionOrThrow(R.styleable.PlanePathView_android_dashGap)
            val dashWidth = a.getDimensionOrThrow(R.styleable.PlanePathView_android_dashWidth)
            pathEffect = DashPathEffect(floatArrayOf(dashWidth, dashGap), 0F)
        }

        plane = a.getDrawableOrThrow(R.styleable.PlanePathView_planeIcon).apply {
            setBounds(0, 0, (intrinsicWidth / 1.5f).toInt(), (intrinsicHeight / 1.5f).toInt())
        }

        radius = 2F
        planePath = Path().apply {
            moveTo(radius, radius)
        }

        a.recycle()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = plane.bounds.height() + paddingTop + paddingBottom
        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val midY = height / 2F
        val radius = midY / 2F
        planePath.run {
            moveTo(midY, midY)
            lineTo(width - midY, midY)
        }

        canvas.drawPath(planePath, planePathPaint)

        canvas.drawCircle(
            midY,
            midY,
            radius,
            dotPaint
        )

        canvas.drawCircle(
            width - midY,
            midY,
            radius,
            dotPaint
        )

        canvas.withTranslation(width / 2F) {
            plane.draw(canvas)
        }
    }
}