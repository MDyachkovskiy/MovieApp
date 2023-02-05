package com.example.kotlin_movieapp.ui.main

import android.view.View
import androidx.viewbinding.ViewBinding

class AppStateRenderer (
    private val binding: ViewBinding
    ) {

    fun render(appState: AppState) {
        val loadingLayoutField = binding::class.java.getDeclaredField("loadingLayout")

        loadingLayoutField.isAccessible = true
        val loadingLayout = loadingLayoutField.get(binding) as? View
        if (loadingLayout != null) {
            when (appState) {
                is AppState.Error -> {
                    loadingLayout.visibility = View.GONE
                }
                is AppState.Loading -> {
                    loadingLayout.visibility = View.VISIBLE
                }
                else -> {
                    loadingLayout.visibility = View.GONE
                }
                }
            }
        }

    fun render(appState: DetailsState) {
        val loadingLayoutField = binding::class.java.getDeclaredField("loadingLayout")

        loadingLayoutField.isAccessible = true
        val loadingLayout = loadingLayoutField.get(binding) as? View
        if (loadingLayout != null) {
            when (appState) {
                is DetailsState.Error -> {
                    loadingLayout.visibility = View.GONE
                }
                is DetailsState.Loading -> {
                    loadingLayout.visibility = View.VISIBLE
                }
                else -> {
                    loadingLayout.visibility = View.GONE
                }
            }
        }
    }
}
