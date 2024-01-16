package com.test.application.movie_details.view.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout

class CustomTitleBehavior(
    context: Context, attrs: AttributeSet
) : CoordinatorLayout.Behavior<TextView>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        if(dependency is AppBarLayout) {
            val ratio = 1.0f - Math.abs(dependency.y) / dependency.totalScrollRange

            if(ratio < 0.5f) {
                child.visibility = View.VISIBLE
                child.alpha = 1.0f - ratio * 2
            } else  {
                child.visibility = View.GONE
            }
        }
        return true
    }
}