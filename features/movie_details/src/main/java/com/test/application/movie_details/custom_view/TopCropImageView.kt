package com.test.application.movie_details.custom_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class TopCropImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyle: Int = 0
) : AppCompatImageView(context, attrs, defStyle) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val drawable: Drawable? = drawable

        if (drawable != null) {
            val width = MeasureSpec.getSize(widthMeasureSpec)
            val diw = drawable.intrinsicWidth

            if (diw > 0) {
                val height = width * drawable.intrinsicHeight / diw
                setMeasuredDimension(width, height)
            } else {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            }
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}