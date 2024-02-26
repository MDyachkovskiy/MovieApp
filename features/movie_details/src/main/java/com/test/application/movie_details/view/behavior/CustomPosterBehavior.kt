package com.test.application.movie_details.view.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.cardview.widget.CardView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.test.application.movie_details.R
import kotlin.math.abs

class CustomPosterBehavior(
    context: Context, attrs: AttributeSet
) : CoordinatorLayout.Behavior<CardView>(context, attrs) {

    private var isAppBarLayoutScrolled = false

    private var lastAppBarLayoutY = Float.MAX_VALUE
    private var threshold = 10f

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
            return handleAppBarLayoutChange(parent, child, dependency)
        }
        return true
    }

    private fun handleAppBarLayoutChange(
        parent: CoordinatorLayout, child: CardView, appBarLayout: AppBarLayout
    ) : Boolean {
        val appBarLayoutY = appBarLayout.y

        if (!isAppBarLayoutScrolled && appBarLayoutY != 0f) {
            isAppBarLayoutScrolled = true
        }

        if(isAppBarLayoutScrolled && shouldUpdatePosition(appBarLayoutY)){
            updateCardViewPosition(parent, child, appBarLayout)
            lastAppBarLayoutY = appBarLayoutY
        }
        return true
    }

    private fun updateCardViewPosition(parent: CoordinatorLayout, child: CardView, appBarLayout: AppBarLayout) {
        val ratio = (appBarLayout.totalScrollRange + appBarLayout.y) / appBarLayout.totalScrollRange

        val newWidth = (originalWidth * (0.5f + ratio * 0.5f)).toInt()
        val newHeight = (originalHeight * (0.5f + ratio * 0.5f)).toInt()
        child.layoutParams.width = newWidth
        child.layoutParams.height = newHeight

        val targetX = parent.resources
            .getDimensionPixelSize(com.test.application.core.R.dimen.margin_16dp_medium)

        val targetY = toolbarHeight + parent.resources
            .getDimensionPixelSize(com.test.application.core.R.dimen.margin_8dp_small)

        val newX = originalX - (originalX - targetX) * (1 - ratio)
        val newY = if (ratio < 1f) {
            originalY + (targetY - originalY) * (1 - ratio)
        } else {
            originalY
        }

        child.x = newX.coerceAtLeast(targetX.toFloat())
        child.y = newY.toFloat()

        Log.d("@@@", "Updated X: ${child.x}, Y: ${child.y}")
        child.requestLayout()
    }

    private fun shouldUpdatePosition(appBarLayoutY: Float): Boolean {
        return abs(lastAppBarLayoutY - appBarLayoutY) > threshold
    }

    private fun initialize(child: CardView, parent: CoordinatorLayout) {
        originalHeight = child.measuredHeight.toFloat()
        originalWidth = child.measuredWidth.toFloat()

        val anchorView = parent.findViewById<View>(R.id.background_image_block)
        val anchorHeight = anchorView.measuredHeight

        originalY = anchorHeight - child.measuredHeight

        val marginStart = parent.resources
            .getDimensionPixelSize(com.test.application.core.R.dimen.margin_24dp_medium)
        originalX = marginStart

        child.x = originalX.toFloat()
        child.y = originalY.toFloat()

        toolbarHeight = parent.findViewById<View>(R.id.toolbar).height
        isInitialize = true

        Log.d("@@@", "Initialized with X: $originalX, Y: $originalY")
    }
}
