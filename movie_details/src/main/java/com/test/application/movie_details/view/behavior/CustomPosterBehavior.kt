package com.test.application.movie_details.view.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.test.application.movie_details.R

class CustomPosterBehavior(
    context: Context, attrs: AttributeSet
) : CoordinatorLayout.Behavior<CardView>(context, attrs) {

    private var originalHeight = 0f
    private var originalWidth = 0f
    private var originalY = 0
    private var originalX = 0
    private var toolbarHeight = 0
    private var isInitialize = false

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: CardView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: CardView,
        layoutDirection: Int
    ): Boolean {
        if (!isInitialize) {
            initialize(child, parent)
        }
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: CardView,
        dependency: View
    ): Boolean {
        if (dependency is AppBarLayout) {
            val ratio = (dependency.totalScrollRange + dependency.y) / dependency.totalScrollRange

            val newWidth = (originalWidth * (0.5f + ratio * 0.5f)).toInt()
            val newHeight = (originalHeight * (0.5f + ratio * 0.5f)).toInt()
            child.layoutParams.width = newWidth
            child.layoutParams.height = newHeight

            val targetX = parent.width - newWidth - parent.resources
                .getDimensionPixelSize(com.test.application.core.R.dimen.margin_16dp_medium)

            val targetY = toolbarHeight + parent.resources
                .getDimensionPixelSize(com.test.application.core.R.dimen.margin_8dp_small)

            val newX = originalX - (originalX - targetX) * (1 - ratio)
            val newY = originalY - (originalY - targetY) * (1 - ratio)
            child.x = newX.coerceAtLeast(targetX.toFloat())
            child.y = newY.coerceAtLeast(targetY.toFloat())

            child.requestLayout()
        }
        return true
    }

    private fun initialize(child: CardView, parent: CoordinatorLayout) {
        originalHeight = child.measuredHeight.toFloat()
        originalWidth = child.measuredWidth.toFloat()

        val anchorView = parent.findViewById<View>(R.id.background_image_block)

        val anchorLocation = IntArray(2)
        anchorView.getLocationOnScreen(anchorLocation)

        originalX = anchorLocation[1] + parent.resources
            .getDimensionPixelSize(com.test.application.core.R.dimen.margin_24dp_medium)
        originalY = anchorLocation[1] - child.measuredHeight

        toolbarHeight = parent.findViewById<View>(R.id.toolbar).height
        isInitialize = true
    }
}
