package com.test.application.movie_details.utils

import android.widget.ImageView

class FavoriteManager(
    private var imageView: ImageView?,
    private val onFavouriteToggle: (isFavourite: Boolean) -> Unit,
    private val onCheckFavourite: (callback: (Boolean) -> Unit) -> Unit
) {

    private var isFavourite: Boolean = false

    init {
        imageView?.setOnClickListener {
            toggleFavourite()
        }
    }

    fun checkFavourite() {
        onCheckFavourite {isFav ->
            isFavourite = isFav
            updateIcon()
        }
    }

    private fun toggleFavourite() {
        isFavourite = !isFavourite
        updateIcon()
        onFavouriteToggle(isFavourite)
    }

    private fun updateIcon() {
        val icon = if(isFavourite) com.test.application.core.R.drawable.ic_favorite else
            com.test.application.core.R.drawable.ic_favorite_border
        imageView?.setImageResource(icon)
    }
}